package org.oves.oveslumnlibraries.Services;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import org.greenrobot.eventbus.EventBus;
import org.oves.oveslumnlibraries.R;
import org.oves.oveslumnlibraries.Utils.OtgDialogFragment;
import org.oves.oveslumnlibraries.Utils.ResponseEvent;
import org.oves.oveslumnlibraries.Utils.ResponseListEvent;
import org.oves.oveslumnlibraries.Utils.UsbService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class LumnLibraryService extends Service {
    //Field variables
    private static final String ACTION_USB_PERMISSION = "org.oves.oveslumnlibraries.USB_PERMISSION";
    private static final long DELAY_MILLISECONDS = 100;

    private final IBinder binder = new LumnLibraryService.MyBinder();
    private UsbDevice device;
    private boolean isOtgCableConnected = false;
    private final BroadcastReceiver lumnlibraryReceiver = new BroadcastReceiver() {
        @SuppressLint("LongLogTag")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)){
                synchronized (this) {
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)){
                        device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if(device != null){
                            //
                            isOtgCableConnected = true;
//                            onRefresh();
                            Log.e("ACTION_USB_DEVICE_ATTACHED ","ATTACHED-" + device);
                        } else {
                            isOtgCableConnected = false;
                            Log.e("ACTION_USB_DEVICE_ATTACHED ","ATTACHED-" + device);
                        }
                    }
                }


            }
            else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)){
                synchronized (this) {
                    device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if(device != null){
                        //
                        isOtgCableConnected = false;
                        Log.e("ACTION_USB_ACCESSORY_DETACHED ","DEATTCHED-" + device);
                    }else {
                        isOtgCableConnected = false;
                        SpannableString s = new SpannableString("OTG");
                        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
//                        menuItem.setTitle(s);
                    }
                }
            }
            else if (ACTION_USB_PERMISSION.equals(action)){
                synchronized (this) {
                    device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {

                        if(device != null){
                            // Device NOT Null
                            Log.e("device != null ","PERMISSION-" + device.toString());
                        } else {
                            // Device Null
                            Log.e("device == null  ","PERMISSION-" + device.toString());
                        }
                    }

                }
            }
        }
    };
    private String parameter;
    private String codeEntryString;
    private String wopidEntryString;
    private String wppidEntryString;
    private String wotpEntryString;
    private String wupnEntryString;
    private String wunEntryString;
    private NotificationManager notificationManager;
    private ResponseListEvent responseListEvent;

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    private StringBuilder stringBuilder;

    public boolean isOtgCableConnected() {
        return isOtgCableConnected;
    }

    public void setOtgCableConnected(boolean otgCableConnected) {
        isOtgCableConnected = otgCableConnected;
    }

    private Context context;
    private UsbManager manager;
    private List<UsbSerialDriver> availableDrivers;
    private IntentFilter filterAttached_and_Detached = null;

    /*Field Variables */
    private PendingIntent mPermissionIntent;
    private String dataReturned;

    public ArrayList<String> getCommandList() {
        return commandList;
    }

    private ArrayList<String> commandList;

    public String getDataReturned() {
        return dataReturned;
    }
    private ArrayList<String> responseList;

    public ArrayList<String> getResponseList() {
        return responseList;
    }

    private byte[] request;
    private byte[] response;
    private UsbSerialPort port;
    private UsbEndpoint endpointIN;
    private UsbEndpoint endpointOUT;
    private int interfaceCount;
    private UsbDeviceConnection connection;
    private UsbSerialDriver driver;
    private FragmentManager fragmentManager;
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
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = R.string.lumnlibrary_service_started;
    private ResponseEvent responseEvent;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //
        context = getApplicationContext();
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);

        filterAttached_and_Detached = new IntentFilter();
        filterAttached_and_Detached.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filterAttached_and_Detached.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filterAttached_and_Detached.addAction(ACTION_USB_PERMISSION);
        registerReceiver(lumnlibraryReceiver, filterAttached_and_Detached);

        startCommandTransmission(">INF<");

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        commandList = new ArrayList<>();
        responseList = new ArrayList<>();
        commandList.addAll(Arrays.asList(COMMANDS));

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
    }

    /**
     * Show a notification while this service is running.
     * Not implemented fully since data is being transmitted to the activity through EventBus
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.lumnlibrary_service_started);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, LumnLibraryService.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.badge_verified)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(getText(R.string.lumnlibrary_service_started))  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Send the notification.
        notificationManager.notify(NOTIFICATION, notification);
    }

    //Start Command Transmission
    public void startCommandTransmission(String parameter) {
        responseList.clear();
        Log.e("Parameter ",  parameter);
        manager = (UsbManager) getApplicationContext().getSystemService(Context.USB_SERVICE);
        availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (!availableDrivers.isEmpty()) {
            driver = availableDrivers.get(0);
            device = driver.getDevice();
            Log.e("Permissions ", String.valueOf(manager.hasPermission(device)));
            if (manager.hasPermission(device)) {
                connection = manager.openDevice(driver.getDevice());
                if (connection == null) {
                    return;
                }

                port = driver.getPorts().get(0);
                try {
                    port.open(connection);
                    port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

                    //Check if port is open
                    if(!port.isOpen()){
                        //Open port connection
                        try {
                            port.open(connection);
                            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

                            //Fetch data
                            try {
                                interfaceCount = device.getInterfaceCount();

                                //Loop through InterfaceCount
                                for (int i = 0; i < interfaceCount; i++){
                                    UsbEndpoint endpoint = device.getInterface(i).getEndpoint(i);

                                    //Check if endpoint type and direction
                                    if (endpoint.getType() == UsbConstants.USB_ENDPOINT_XFER_CONTROL){
                                        //Check direction
                                        if (endpoint.getDirection() == UsbConstants.USB_DIR_OUT){
                                            endpointOUT = endpoint;
                                            //Get parameter bytes and write to port
                                            request = new byte[64];
                                            request = parameter.getBytes();
                                            port.write(request, 100);
                                            Thread.sleep(100);
                                        }
                                        else {
                                            endpointIN = endpoint;
                                            response = new byte[64];
                                            int dataLength = port.read(response, 100);
                                            //Check size
                                            if (dataLength > 0){
                                                Log.e("XFER_CONTROL dataLen", String.valueOf(dataLength));
                                            }
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        //Port is open
                        try {
                            interfaceCount = device.getInterfaceCount();

                            //Loop through InterfaceCount
                            for (int i = 0; i < interfaceCount; i++){
                                UsbEndpoint endpoint = device.getInterface(i).getEndpoint(i);
                                if (endpoint.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK){
                                    //Check direction
                                    if (endpoint.getDirection() == UsbConstants.USB_DIR_OUT){
                                        endpointOUT = endpoint;
                                        //Get parameter bytes and write to port
                                        request = new byte[64];
                                        request = parameter.getBytes();
                                        port.write(request, 100);
                                        Thread.sleep(100);
                                    }
                                    else {
                                        endpointIN = endpoint;
                                        try {
                                            request = new byte[64];
                                            request = parameter.getBytes();
                                            port.write(request, 100);
                                            Thread.sleep(100);
                                        } catch (IOException | InterruptedException exception) {
                                            exception.printStackTrace();
                                        }


                                        try {
                                            response = new byte[64];
                                            int dataLength = port.read(response, 100);
                                            Log.e("Data Length ", String.valueOf(dataLength));
                                            //Check size
                                            if (dataLength > 0){
                                                ByteBuffer byteBuffer = null;
                                                stringBuilder = new StringBuilder();

                                                dataReturned = new String(response, StandardCharsets.UTF_8);
                                                stringBuilder.append(dataReturned);
                                                Log.e("XFER_BULK dataLen2", "Length " + dataLength + " " + dataReturned + " " + stringBuilder);


                                                int start = 0;
                                                int end = 0;
                                                if (response.length > 64){
                                                    byteBuffer.put(response, 0, 31);
                                                }
                                                //add to responseList data
                                                responseList.add(dataReturned);

                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Log.e("ResponseList4 ", String.valueOf(responseList));

                                                        //EventBus
                                                        responseListEvent = new ResponseListEvent(responseList);
                                                        EventBus.getDefault().post(responseListEvent);
//                                                        responseEvent = new ResponseEvent(responseList.get(0));
                                                        responseEvent = new ResponseEvent(dataReturned);
                                                        EventBus.getDefault().post(responseEvent);
                                                    }
                                                }, 10);
                                            }
                                        } catch (IOException exception) {
                                            exception.printStackTrace();
                                        }

                                    }
                                }
                            }

                        } catch (Exception exception) {
                            Log.e("Exception ", exception.getMessage());
                        }
                    }


                } catch (IOException exception) {
                    exception.printStackTrace();
                }


                //////////////////////////////////
            }
            else {
                Log.e("Dialog 1", "!manager.hasPermissions");

                mPermissionIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        0, new Intent(Context.USB_SERVICE), 0);
                manager.requestPermission(device, mPermissionIntent);


            }
        }
        else {
            Log.e("Dialog 2", "");
            showDisconnectedOtgDialog();
        }

    }

    public void checkDataAtPosition(String item, int position) throws NullPointerException{
        //check positions needing parameters
        if (position <= 11){
            //send command
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(item);
                }
            }, DELAY_MILLISECONDS);
        } else {
            parameter = "";
            if (item.startsWith(">WOPID<")){
                wopidEntryString = codeEntryString;
                parameter = ">WOPID<" + wopidEntryString + "<";
                Timber.e("wopidEntryString" + wopidEntryString);
                //Input OEM ID
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startCommandTransmission(parameter);
                    }
                }, DELAY_MILLISECONDS);
            }
            else if(item.startsWith(">WPPID<")){
                wppidEntryString = codeEntryString;
                parameter = ">WPPID:" + wppidEntryString + "<";
                Timber.e("wppidEntryString" + wppidEntryString);
                //Input OEM ID
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startCommandTransmission(parameter);
                    }
                }, DELAY_MILLISECONDS);
                Timber.e("Param needed " + item + position);
            }
            else if(item.startsWith(">WOTP<")){
                wotpEntryString = codeEntryString;
                parameter = ">WOTP:" + wotpEntryString + "<";
                Timber.e("wotpidEntryString" + wotpEntryString);
                //Input OEM ID
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startCommandTransmission(parameter);
                    }
                }, DELAY_MILLISECONDS);
                Timber.e("Param needed " + item + position);
            }
            else if(item.startsWith(">WUPN<")){
                wupnEntryString = codeEntryString;
                parameter = ">WUPN:" + wupnEntryString + "<";
                Timber.e("wupnEntryString" + wupnEntryString);
                //Input OEM ID
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startCommandTransmission(parameter);
                    }
                }, DELAY_MILLISECONDS);
                Timber.e("Param needed " + item + position);

            }
            else if(item.startsWith(">WUN")){
                wunEntryString = codeEntryString;
                parameter = ">WUN:" + wunEntryString + "<";
                Timber.e("wunEntryString" + wunEntryString);
                //Input OEM ID
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startCommandTransmission(parameter);
                    }
                }, DELAY_MILLISECONDS);
            }
            codeEntryString  = "";
        }

    }

    public void writeToDevice(String commandPrefix, String data){
        parameter = "";
        if (commandPrefix.startsWith(">WOPID<")){
            parameter = ">WOPID<" + data + "<";
            Timber.e("wopidEntryString" + wopidEntryString);
            //Input OEM ID
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(parameter);
                }
            }, DELAY_MILLISECONDS);
        }
        else if(commandPrefix.startsWith(">WPPID<")){
            parameter = ">WPPID:" + data + "<";
            Timber.e("wppidEntryString" + wppidEntryString);
            //Input OEM ID
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(parameter);
                }
            }, DELAY_MILLISECONDS);
        }
        else if(commandPrefix.startsWith(">WOTP<")){
            parameter = ">WOTP:" + data + "<";
            Timber.e("wotpidEntryString" + wotpEntryString);
            //Input OEM ID
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(parameter);
                }
            }, DELAY_MILLISECONDS);
        }
        else if(commandPrefix.startsWith(">WUPN<")){
            parameter = ">WUPN:" + data + "<";
            Timber.e("wupnEntryString" + wupnEntryString);
            //Input OEM ID
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(parameter);
                }
            }, DELAY_MILLISECONDS);

        } else if(commandPrefix.startsWith(">WUN")){
            parameter = ">WUN:" + data + "<";
            Timber.e("wunEntryString" + wunEntryString);
            //Input OEM ID
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startCommandTransmission(parameter);
                }
            }, DELAY_MILLISECONDS);
        }
    }

    //Show Disconnected OTG cable connection
    private void showDisconnectedOtgDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("OTG Connection Failure")
                .setMessage("Check if OTG cable is connected securely on the device and peripheral")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create();
    }

    private class OtgDialogFragment extends AppCompatDialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("OTG Connection Failure")
                    .setMessage("Check if OTG cable is connected securely on the device and peripheral")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    });
            return builder.create();
        }
    }

    //My Binder
    public class MyBinder extends Binder {
        public LumnLibraryService getService() {
            return LumnLibraryService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Check connection
        if (isOtgCableConnected){
            this.stopSelf();
        }

        // Cancel the persistent notification.
        notificationManager.cancel(NOTIFICATION);

        // Tell the user we stopped.
        Toast.makeText(this, R.string.lumnlibrary_service_started, Toast.LENGTH_SHORT).show();


    }


}
