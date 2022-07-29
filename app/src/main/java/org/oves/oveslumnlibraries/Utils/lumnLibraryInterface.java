package org.oves.oveslumnlibraries.Utils;

import android.content.IntentFilter;
import android.hardware.usb.UsbManager;

import com.hoho.android.usbserial.driver.UsbSerialDriver;

import java.util.ArrayList;
import java.util.List;

public interface lumnLibraryInterface {

    //Get Usb Manager
    UsbManager getUsbManager(final UsbManager usbManager);

    //Get Intent Filters
    void getIntentFilters(IntentFilter intentFilter);

    //Init Lists
    void InitLists(ArrayList<String> commandsList, ArrayList<String> responsesList);

    //Get Available drivers
    List<UsbSerialDriver> getAvailableDrivers(List<UsbSerialDriver> availableDriver);

    //Check ResponseList Data
    ArrayList<String> getResponseListData(ArrayList<String> responseListData );

    //Start communication
    void startSerialCommunication(UsbManager usbManager, List<UsbSerialDriver> availableDriver);

    //Get CommandList
    String[] getCommandList(String[] commands);

}

//@Override
//    public void getUsbManager() {
//        manager = (UsbManager) getApplicationContext().getSystemService(Context.USB_SERVICE);
//    }
//
//    @Override
//    public void getIntentFilters() {
//        filterAttached_and_detached = new IntentFilter();
//        filterAttached_and_detached.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
//        filterAttached_and_detached.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        filterAttached_and_detached.addAction(ACTION_USB_PERMISSION);
//    }
//
//    @Override
//    public void InitLists() {
//        //Init Lists
//        commandList = new ArrayList<>();
//        responseList = new ArrayList<>();
//        commandList.addAll(Arrays.asList(COMMANDS));
//    }
//
//    @Override
//    public void getAvailableDrivers() {
//        availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
//    }
//
//    @Override
//    public void getResponseListData() {
//        if (responseList.size() != 0){
//            Log.e("Response Item >", responseList.toString());
//
//            days.setText("Data:3 " + dataReturned);
//
//        }
//        else {
//            Log.e("Response Item <", responseList.toString());
//            days.setText("Something went wrong, please try again");
//        }
//    }
//
//    @Override
//    public void startSerialCommunication() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (!port.isOpen()){
//                    try {
//                        port.open(connection);
//                        port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
//
//                    } catch (Exception exception){
//                        Log.e("Exception ", exception.getMessage());
//                    }
//                } else {
//                    try {
//                        interfaceCount = device.getInterfaceCount();
//
//                        //Loop through InterfaceCount
//                        for (int i = 0; i < interfaceCount; i++){
//                            UsbEndpoint endpoint = device.getInterface(i).getEndpoint(i);
//                            if (endpoint.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK){
//                                //Check direction
//                                if (endpoint.getDirection() == UsbConstants.USB_DIR_OUT){
//                                    endpointOUT = endpoint;
//                                    //Get parameter bytes and write to port
//                                    request = new byte[64];
//                                    request = parameter.getBytes();
//                                    port.write(request, 100);
//                                    Thread.sleep(100);
//                                }
//                                else {
//                                    endpointIN = endpoint;
//                                    try {
//                                        request = new byte[64];
//                                        request = parameter.getBytes();
//                                        port.write(request, 100);
//                                        Thread.sleep(100);
//                                    } catch (IOException | InterruptedException exception) {
//                                        exception.printStackTrace();
//                                    }
//
//
//                                    try {
//                                        response = new byte[64];
//                                        int dataLength = port.read(response, 100);
//                                        //Check size
//                                        if (dataLength > 0){
//                                            ByteBuffer byteBuffer = null;
//                                            StringBuilder stringBuilder = new StringBuilder();
//
//                                            dataReturned = new String(response, StandardCharsets.UTF_8);
//                                            Log.e("XFER_BULK dataLen2", "Length " + dataLength + " " + dataReturned);
//
//                                            stringBuilder.append(dataReturned);
//                                            int start = 0;
//                                            int end = 0;
//                                            if (response.length > 64){
//                                                byteBuffer.put(response, 0, 31);
//                                            }
//                                            //add to responseList data
//                                            responseList.add(dataReturned);
//
//                                            new Handler().postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    days.setText("Data4: " + dataReturned);
//                                                }
//                                            }, 10);
//                                        }
//                                    } catch (IOException exception) {
//                                        exception.printStackTrace();
//                                    }
//
//                                }
//                            }
//                        }
//
//                    } catch (Exception exception) {
//                        Log.e("Exception ", exception.getMessage());
//                    }
//                }
//            }
//        }, DELAY_MILLISECONDS);
//    }
