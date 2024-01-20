## Robot Tasker Client

Robot Tasker Client is a Java-based Android application created to establish compatible communication with dedicated API.   
Compatible API which this client is communicating with is shared [here](https://github.com/sebastianbrzustowicz/Robot-tasker-API).   
Minimum SDK/API level is 24, which is Android 7.0 Nougat (cumulative coverage 96.8% of all devices in use).  
Application was created with Android Studio.  
### Disclaimer
Complete version which guarantee best performance is not available publicly.   
Only alpha version of app with license restrictions is available.   
Please contact me if you are interested in cooperation.   
I am willing to help.  

## Views
Some of the implemented views:

<p align="center">
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/c24fe782-22d4-45cf-9f8f-2791ec4aa81c" width="32%" height="auto"/>
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/ce4e8177-8fac-4eec-a824-479edebc7ef1" width="32%" height="auto"/>
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/036e664b-ceb0-418d-9342-536f18a98cb5" width="32%" height="auto"/>
</p>
<div align=center>
<img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/1c7cd0c5-4b2b-41ff-9db1-bdf2af24e347" width="74%" height="auto"/>
</div>

## Hierarchy of views

A hierarchy of XML layout files is as follows:

```
- `res`
  - `layout`
    - `activity_login.xml`
      - `activity_menu.xml`
        - `activity_vehicle_menu.xml`
          - `activity_vehicle_runtime.xml`
        - `activity_register_vehicle.xml`
          - `activity_register_custom_vehicle.xml`
    - `activity_register.xml`
```

## Transferred data

The data sent to the API depends on the type of vehicle. In this case, it is a quadcopter.     
Handshake should be established between server and client according to data order.     
The data is sent in raw string format but its values stands for these variables:

```
CLIENT                                  // <- fixed prefix for client message
4436ed9a-5228-46c0-b825-6d0a3cd90437    // <- vehicleId
1                                       // <- mode
0                                       // <- vtol
0                                       // <- x
0                                       // <- y
0                                       // <- alt
0                                       // <- yaw
false                                   // <- camTrig
false                                   // <- camTog
0                                       // <- camPitch
false                                   // <- clamp
END                                     // <- fixed ending statement of message
```

## Tests

Some simple instrumented AndroidJUnit4 tests have been implemented:
```java
useAppContext()
loginButtonClicked_Success()
loginButtonClicked_Failed()
swapToRegisterButtonClicked()
registerButtonClicked_Success()
registerButtonClicked_Failed()
swapToLoginButtonClicked()
```

## License

Robot Tasker Client is released under the CC BY-NC-ND 4.0 license.

## Author

Sebastian Brzustowicz &lt;Se.Brzustowicz@gmail.com&gt;
