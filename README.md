# OvesLumnLibraries
This repo is meant to create a sample of serail communication application to communicate with OVES LUMN devices 

## Implementation in Android Studio

### Using LumnLibraryService.java service class.
In a new project, the simplest way to handle serial communication and data updates is Using EventBus events
which have been incorporated to the sample project in this repository. Kindly chech the application sample
project for the LumnLibraryService class in the Services folder. 

### Some screenshots of the sample project can be seen below:
This is an example READ with the sample application
![READ from device](https://github.com/EricWanjohi/OvesLumnLibraries/tree/main/SampleAppImages/Screenshot_20220729-120343_LUMNLIB.jpg)

This is an example WRITE in the sample application
![WRITE to device](https://github.com/EricWanjohi/OvesLumnLibraries/tree/main/SampleAppImages/Screenshot_20220729-120416_LUMNLIB.jpg)

### Steps to using the service class
  1. Add the service class to your project and sync project
  
  2. In the activity class intended to use the service class, Make sure to implement a ServiceConnection
  so as to bind the activity class with an instance of the service class once the service class is initialized.
  
    ```
       private final ServiceConnection lumnLibraryServiceConnection = new ServiceConnection() {
          @Override
          public void onServiceConnected(ComponentName componentName, IBinder service) {
              LumnLibraryService.MyBinder myBinder = (LumnLibraryService.MyBinder) service;
              lumnLibraryService = myBinder.getService();
          }
         @Override
          public void onServiceDisconnected(ComponentName componentName) {
              lumnLibraryService.stopSelf();
          }
      };
    ```
    
  3. Make sure to also implement EventBus objects so as to get updates from the service class.
    
    ```
      // This method will be called when a ResponseEvent is posted by the LumnLibraryService
      @Subscribe(threadMode = ThreadMode.MAIN)
      public void onResponseEvent(ResponseEvent event) {
          responseEvent = event;
          dataReturned = responseEvent.message;
          days.setText("Response: " + dataReturned);
          
          //This can be shown to user or used for debugging
          Toasty.success(getApplicationContext(), dataReturned, Toasty.LENGTH_SHORT).show();
      }

      // This method will be called when a ResponseListEvent is posted by the LumnLibraryService
      @Subscribe(threadMode = ThreadMode.MAIN)
      public void onResponseListEvent(ResponseListEvent event) {
          responseListEvent = event;
          responseList = responseListEvent.lumnResponseList;
          
          //This can be shown to user or used for debugging
          Toasty.success(getApplicationContext(), String.valueOf(responseList), Toast.LENGTH_SHORT).show();
      }
      
    ```
    
  4. The last step is overriding onStop(), onResume() and onDestroy() methods for binding the service to the activity class in this case
  
    ```
        @Override
        protected void onStart() {
            super.onStart();
            //Register EventBus here
            EventBus.getDefault().register(this);
            
            //Bind LumnLibraryService
            bindService(new Intent(getApplicationContext(), LumnLibraryService.class), lumnLibraryServiceConnection, Context.BIND_AUTO_CREATE);
        }

        @Override
        protected void onResume() {
            super.onResume();
            
            //Bind LumnLibraryService
            bindService(new Intent(getApplicationContext(), LumnLibraryService.class), lumnLibraryServiceConnection, Context.BIND_AUTO_CREATE);
        }

        @Override
        protected void onStop() {
        
            //Unregister EventBus here
            EventBus.getDefault().unregister(this);
            super.onStop();
            
            //Unbind lumnLibraryServiceConnection here
            unbindService(lumnLibraryServiceConnection);
        }
    ```
    
    5. With the setup as indicated above, you should be able to WRITE to the device using the method
    
      ### WRITE method
      ```
        writeToDevice(String commandPrefix, String data)
        
      ```
      NB// commandPrefix variable is a normal command item e.g. ">HAND<"
           data in this case is data that you want to WRITE to the device e.g customer name, phone number etc
           
      ### READ method
       
      ```
        startCommandTransmission(String parameter)
        
      ```
      NB// parameter is a string variable carrying the command to read certain data from the device. e.eg ">PS<", ">LVC<" etc.
      
  With the setup above you should be able to communicate with the device via serial cable and get and send data in full duplex.
       



### Steps using library
    1. Add .aar (in the Library folder) to your project. If there is no libs folder you can create one.
        This is done by adding the file in the app/libs folder.

    2. In the module (build.gradle) add the dependency like below
        implementation fileTree(include: ['*.aar'], dir: 'libs')

    3. Sync the project and extend your Activity from the Library.

        ```
            public class TestActivity extends org.oves.lumnlib.LumnLibraryActivity {

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                }
            }
        ```
    This library will offer a simple token entry user interface to input a new token and send to the device to update the PAYG days


# UART Port Settings
Device (DEV): Lumn series product with a 2-wire UART port exposed via connector

Client App (APP): External Unit that connects to DEV via UART port

- Baud Rate 9600bps
- Start Bit 1 bit
- Data Frame 8 bit
- Stop Bit 1 bit

## Data Format
Ascii String enclosed by special escape sequence

APP request / command format <[0-9][A-Z]>;, allowed ASCII character sets delineated by < and >
- Valid command string must start with > and end with <
- Device response format: <[0-9][A-Z]>
- Valid response string must start with < and end with >

# Handshake sequences

## APP side

1) Connect to UART (OTG/USB, RS232 etc.)

2) APP detects DEV broadcast: read from port continuous stream of '<'NEW'>'

3) APP requests attention with a write to part >HAND<

4) APP reads a single \<\OK\>\ followed by silence, confirming DEV in attention mode

5) APP writes an instruction >[Request]<, details dee below

6) APP reads a <[Response]>

7) APP relinquishes attention of DEV via: UART disconnection, 120S inactivity, or command \<\END\>

## DEV side

0) Detect UART (via OTG/USB, RS232 etc.) pull-up signal

1) DEV initiates and repeats <\NEW> and followed by a read.

2) DEV reads and receives attention request string >HAND<

3) DEV sends <\OK>, and goes to attention mode, reads from UART per bus activity interrupt

4) DEV revives a >[Request]<

5) DEV writes a <[Response]> based on the request instruction details below

6) DEV regains broadcast mode with: UART disconnection, 120S inactivity, or command >END<>

Table bellow are the detail format and semantics of the [Request] and [Response], making up the public portion of the instruction set:

| APP Request            | DEV Response                               | Meaning                                                                                          |
|--------------------------------|--------------------------------------|--------------------------------------------------------------------------------------------------|
| >END<                  | null                          | end of session, give up attention                                                                |
| >HAND<                 | < OK>                          | DEV acknowledges APP presence, and goes into attention mode                                         |
| >LCS<                  | < LCS:00000>                   | Accumulated Full-Discharge Cycles (5 Digit Decimal)                                              |
| >LVC<                  | < LVC:A1A2A3A4A5A6A7A8>      | The last valid token expressed in hex, U64 encoded
| >MODE<                 | < MODE:x>                      | Current DEV PAYG Mode: 0=allow negative credit, 1=non-negative credit, 2=reserved                |
| >OCS<                  | < OCS: ENABLED> or < OCS:DISABLED> | Output control state: ["ENABLED"] / ["DISABLED"] (note the leading space in the 8-letter string) |
| >OPID<                 | < OPID:2001704660001A>         | 14 digit Device OEM_ID.   Shorter ID string are buffered with empty spaces                       |
| >OTPSN<                | < OTPSN:00000>                 | Count of past correct code entries, 5-digit decimal                                              |
| >PPID<                 | < PPID:12345678901234567890>   | 20 digit PAYG Operator_ID.   Shorter ID string are buffered with empty spaces                    |
| >PS<                   | < PS:PAYG> or < PS:FREE> | Device PAYG credit credit mode: < PS:PAYG> / < PS:FREE>                                            |
| >RPD<                  | < RPD: 0000D00H00M> | Remaining paid days before device is disabled. Decimal number
|>WOPID:2001704660001A<|< RE:WOK> (OEM ID entry succeed) < RE:FAIL>  (OEM ID entry failed) | Input OEM ID, a 14-digit ASCII string
|>WPPID:12345678901234567890<|< RE:WOK> (PAYG ID entry succeed) < RE:FAIL>  (PAYG ID entry failed) | Input PAYG ID, a 20-digit ASCII string
|>WOTP:012345678901234567890<|< RE:WAIT> (wait for device token processing)  / < RE:FAIL>  (token code entry failed) < RE:xxxx> (token entry succeeded, credit days updated to XXX, an integer value)| Input 21-digit decimal code,successful code entry returns updated credit days?example < RE:0001>?
| >WUPN:+86-13366997615< | < WOK> | Save phone number into device, 20 digit ["+", "-", 0-9]                                          |
| >WUN:XIAOMING<         | < WOK> | Save customer name 20 letter max                                                                 |



