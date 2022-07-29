package org.oves.oveslumnlibraries.Utils;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Binder;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.util.List;

public class UsbService extends Service {
    private static final String ACTION_USB_PERMISSION = "com.DeviceScanner.components.USB_PERMISSION";
    private final byte[] request = new byte[64];
    private final byte[] response = new byte[64];
    private final IBinder binder = new MyBinder();
    //Fields
    private PendingIntent permissionsPendingIntent;
    private UsbDeviceConnection connection;
    private UsbSerialDriver driver;
    private UsbDevice device;
    private Context context;
    private UsbManager manager;
    private List<UsbSerialDriver> availableDrivers;
    private IntentFilter filterAttached_and_Detached = null;
    private List<String> commandList;
    private UsbSerialPort port;
    private boolean isOtgCableConnected = false;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if(device != null){
                        //
                        isOtgCableConnected = false;
                        Log.e("1","DEATTCHED-" + device);
                    }else {
                        isOtgCableConnected = false;
                        SpannableString s = new SpannableString("OTG");
                        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
//                        menuItem.setTitle(s);
                    }
                }
            }
//
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                synchronized (this) {

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if(device != null){
                            //
                            isOtgCableConnected = true;
//                            onRefresh();
                            Log.e("1","ATTACHED-" + device);
                        } else {
                            isOtgCableConnected = false;
                            Log.e("1","ATTACHED-" + device);
                        }
                    }
                    else {
                        PendingIntent mPermissionIntent;
                        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_ONE_SHOT);
                        manager.requestPermission(device, mPermissionIntent);

                    }

                }
            }
//
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {

                        if(device != null){
                            //
//                            isPermissionGranted = true;
                            Log.e("1","PERMISSION-" + device.toString());
                        }
                    }

                }
            }

        }
    };

    public boolean isOtgCableConnected() {
        return isOtgCableConnected;
    }

    public void setOtgCableConnected(boolean otgCableConnected) {
        isOtgCableConnected = otgCableConnected;
    }

    private void UsbService(){}

//    private boolean isOtgCableConnected(){
//        return isOtgCableConnected;
//    }

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
        //
        filterAttached_and_Detached = new IntentFilter();
        filterAttached_and_Detached.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filterAttached_and_Detached.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filterAttached_and_Detached.addAction(ACTION_USB_PERMISSION);
        registerReceiver(mUsbReceiver, filterAttached_and_Detached);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Check connection
        if (isOtgCableConnected){
            this.stopSelf();
        }

    }

    public class MyBinder extends Binder {
        public UsbService getService() {
            return UsbService.this;
        }
    }
}
