package org.oves.oveslumnlibraries.UI;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.oves.oveslumnlibraries.R;
import org.oves.oveslumnlibraries.Services.LumnLibraryService;
import org.oves.oveslumnlibraries.Utils.ResponseEvent;
import org.oves.oveslumnlibraries.Utils.ResponseListEvent;
import org.oves.oveslumnlibraries.Utils.Tools;
import org.oves.oveslumnlibraries.Utils.lumnLibraryInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class OvesLumnLibrariesActivity extends AppCompatActivity implements View.OnClickListener{
    //Field variables
    private PendingIntent mPermissionIntent;
    private String codeEntryString;
    private EditText codeEntry;
    private Button sendCodeButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private View navigation_header;
    private TextView email, name;
    private String userEmail, userRole;
    private CircularImageView avatar;
    private NestedScrollView nested_scroll_view;
    private SwipeRefreshLayout swipe_refresh;
    private String loginToken;
    private String userRoleID;
    private Bundle fragbundle;
    private String [] commands = new String[0];
    private String testCommand = "<INF>";
    private View view;
    private String resp;
    private ViewGroup viewGroup;
    private UsbManager manager;
    private static final String ACTION_USB_PERMISSION  = "org.oves.oveslumnlibrary.USB_PERMISSION";
    private IntentFilter filterAttached_and_detached;
    public static final int PERMISSION_REQUEST_CODE = 1000;
    private boolean isPermissionGranted = false;
    private ArrayList<String> commandList;
    private ArrayList<String> responseList;
    private List<UsbSerialDriver> availableDrivers;
    private static final long DELAY_MILLISECONDS = 100;
    private ResponseListEvent responseListEvent;

    public String[] getCOMMANDS() {
        return COMMANDS;
    }

    public final String[] COMMANDS = {
            ">END<",
            ">HAND<",
            ">LCS<",
            ">LVC<",
            ">MODE<",
            ">OCS<",
            ">OPID<",
            ">OTPSN<",
            ">PPID<",
            ">PS<",
            ">RPD<",
            ">INF<",
            ">WOPID<",
            ">WPPID<",
            ">WOTP<",
            ">WUPN<",
            ">WUN<"

    };
    private MaterialSpinner spinner;
    private String commandResData;
    private String wopidEntryString = "";
    private String wppidEntryString = "";
    private String wupnEntryString = "";
    private String wunEntryString = "";
    private String wotpEntryString = "";
    private String parameter;
    public String dataReturned;
    private TextView days;
    private UsbSerialDriver driver;
    private UsbDeviceConnection connection;
    private UsbSerialPort port;
    private boolean isPortOpen = false;
    private int interfaceCount;
    private UsbEndpoint endpoint;
    private UsbEndpoint endpointOUT;
    private UsbEndpoint endpointIN;
    private UsbDevice device;
    private byte[] request;
    private byte[] response;
    private LinearLayout input_entry;
    private boolean isUsbConnected;
    private boolean isOtgCableConnected = false;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if(device != null){
                        //
                        isUsbConnected = true;
                        isOtgCableConnected = true;
                        Log.e("1","DEATTCHED-" + device);
                    }else {
                        isUsbConnected = false;
                        isOtgCableConnected = false;
                    }
                }
            }
            //
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                synchronized (this) {
                    device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {

                        if(device != null){
                            //
                            isUsbConnected = true;
                            isOtgCableConnected = true;
//                        onRefresh();
                            Log.e("1","ATTACHED-" + device);
                        } else {
                            isUsbConnected = false;
                            isOtgCableConnected = false;
                        }
                    }
                    else {
                        PendingIntent mPermissionIntent;
                        mPermissionIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_ONE_SHOT);
                        manager.requestPermission(device, mPermissionIntent);

                    }

                }
            }
            //
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {

                        if(device != null){
                            //
                            isPermissionGranted = true;
                            Log.e("1","PERMISSION-" + device);
                        } else {
                            isPermissionGranted = false;
                            Log.e("1","PERMISSION-" + device);
                        }
                    }

                }
            }

        }
    };
    private org.oves.oveslumnlibraries.Utils.lumnLibraryInterface lumnLibraryInterface;
    private LumnLibraryService lumnLibraryService;
    private final ServiceConnection lumnLibraryServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            LumnLibraryService.MyBinder myBinder = (LumnLibraryService.MyBinder) service;
            lumnLibraryService = myBinder.getService();
            isOtgCableConnected = lumnLibraryService.isOtgCableConnected();
            responseList = lumnLibraryService.getResponseList();
            commandList = lumnLibraryService.getCommandList();
            dataReturned = lumnLibraryService.getDataReturned();

            Timber.e("Data Returned %s", dataReturned);
            Timber.e("responseList Returned %s", String.valueOf(responseList));
//            Log.e("Data Returned ", dataReturned);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isOtgCableConnected = lumnLibraryService.isOtgCableConnected();
            lumnLibraryService.stopSelf();
        }
    };
    private ResponseEvent responseEvent;
    private String data = "0700206857";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lumn_layout);

        //InitViews
        initViews();
    }

    private void initViews() {

        //Toolbar setup
//        getSupportActionBar().setTitle("Home");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Oves Lumn Libraries");
        Tools.setSystemBarColor(this);

        //Init Views
        sendCodeButton = findViewById(R.id.sendCodes);
        codeEntry = findViewById(R.id.codeEntrys);
        spinner = findViewById(R.id.spinner);
        input_entry = findViewById(R.id.input_entry);
        days = findViewById(R.id.data);

        //Init Command Spinner
        initCommandsSpinner();

        //get Manager
        manager =  (UsbManager) getApplicationContext().getSystemService(Context.USB_SERVICE);

        //get Intent Filters
        filterAttached_and_detached = new IntentFilter();
        filterAttached_and_detached.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filterAttached_and_detached.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filterAttached_and_detached.addAction(ACTION_USB_PERMISSION);

        //Init Lists
        commandList = new ArrayList<>();
        responseList = new ArrayList<>();
        commandList.addAll(Arrays.asList(COMMANDS));

        days.setText(String.valueOf(dataReturned));

    }

    // This method will be called when a ResponseEvent is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResponseEvent(ResponseEvent event) {
        responseEvent = event;
        dataReturned = responseEvent.message;
        days.setText("Response: " + dataReturned);
//        Toasty.success(getApplicationContext(), dataReturned, Toast.LENGTH_SHORT).show();
    }

    // This method will be called when a ResponseListEvent is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResponseListEvent(ResponseListEvent event) {
        responseListEvent = event;
        responseList = responseListEvent.lumnResponseList;
//        Toasty.success(getApplicationContext(), String.valueOf(responseList), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Remember to Register EventBus here
        EventBus.getDefault().register(this);
        //Bind LumnLibraryService
        bindService(new Intent(getApplicationContext(), LumnLibraryService.class), lumnLibraryServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent(getApplicationContext(), LumnLibraryService.class), lumnLibraryServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        //Remember to Unregister EventBus here
        EventBus.getDefault().unregister(this);
        super.onStop();
        //Remember to Unbind lumnLibraryServiceConnection here
        unbindService(lumnLibraryServiceConnection);
    }

    //Practical Example use with a Spinner.
    public void initCommandsSpinner() {
        spinner.setItems(COMMANDS);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Item ", item);
                        spinner.setSelected(true);
//                        view.setHintColor(R.color.colorPrimaryDark);
                        MaterialSpinner selectedView = new MaterialSpinner(getApplicationContext()) ;
                        selectedView.setBackgroundColor(R.color.colorPrimaryDark);


                        if (position <= 11 ){
                            input_entry.setVisibility(View.GONE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("position ", "position <= 11");
                                    lumnLibraryService.startCommandTransmission(item);
                                    lumnLibraryService.startCommandTransmission(item);
                                }
                            }, DELAY_MILLISECONDS);
                        }
                        else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("position ", "position > 11");
                                    lumnLibraryService.checkDataAtPosition(item, position);
                                    lumnLibraryService.checkDataAtPosition(item, position);
                                }
                            }, 10);
                        }
                    }
                }, DELAY_MILLISECONDS);
                spinner.setEnabled(true);
                spinner.setArrowColor(R.color.colorPrimary);
                Log.e("Selected Index ", String.valueOf(spinner.getSelectedIndex()));



                //Check responseList data
                Log.e("ResponseList ", String.valueOf(responseList));
//                if (responseList.size() != 0){
//                    Log.e("Response Item >", responseList.toString());
//                    dataReturned = responseEvent.message;
//
//                    days.setText("Response: " + dataReturned);
//
//                }
//                else {
//                    Log.e("Response Item <", responseList.toString());
//                    days.setText("Something went wrong, please check the OTG cable connection and try again");
//                }
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
//                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        String item = (String) spinner.getItems().get(spinner.getSelectedIndex());
        int index = spinner.getSelectedIndex();
        //Check view item
        if (view.getId() == R.id.sendCodes){
            codeEntryString = codeEntry.getText().toString();
            //Check command selected
            Object it = spinner.getItems().get(index);
            Log.e("Command data ", item + " " + index);
            lumnLibraryService.checkDataAtPosition(item, index);
            days.setText(resp);
        }
        else if (view.getId() == R.id.spinner_item){
            int position = index; //TODO
            toggleSpinnerItems(position, item);
        }
    }

    private void toggleSpinnerItems(int position, String item) {
        if (position <= 11 ){
            input_entry.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lumnLibraryService.startCommandTransmission(item);
                    /**
                     * In my example here i use items in a Spinner but you can choose to use any other method of display*/
//                    lumnLibraryService.startCommandTransmission(item);
                }
            }, DELAY_MILLISECONDS);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    lumnLibraryService.checkDataAtPosition(item, position);
                    lumnLibraryService.writeToDevice(item, data  );
                    /**
                     * Note the second parameter that is dynamic meaning when writing to the device, you need to be very care what data you are appending to which command in your usecase. As you can see my data variable is static in this case but makes the communication with the device.
                     * */
                }
            }, 10);
        }
    }
}