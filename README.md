## Robot Tasker Client

Robot Tasker client is a Java-based Android application created to establish compatible communication with dedicated API.   
Compatible API which this client is communicating with is shared [here](https://github.com/sebastianbrzustowicz/Robot-tasker-API).   
Application was created with Android Studio.

## Views
Some of the implemented views:

<p align="center">
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/c24fe782-22d4-45cf-9f8f-2791ec4aa81c" width="32%" height="auto"/>
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/ce4e8177-8fac-4eec-a824-479edebc7ef1" width="32%" height="auto"/>
  <img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/036e664b-ceb0-418d-9342-536f18a98cb5" width="32%" height="auto"/>
</p>
<div align=center>
<img src="https://github.com/sebastianbrzustowicz/Robot-tasker-client/assets/66909222/1c7cd0c5-4b2b-41ff-9db1-bdf2af24e347" width="72%" height="auto"/>
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

The data that are sent to the API depend on the type of vehicle. In this case, it is a quadcopter:

```json
{
  "vehicleId": "e218e18c-9e1c-11ee-8c90-0242ac120002",
  "mode": 1,
  "vtol": 0,
  "x": 1,
  "y": 0,
  "alt": 1,
  "yaw": 0,
  "camTrig": 0,
  "camTog": 0,
  "camPitch": 0,
  "clamp": 0
}
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

Robot-tasker-client is released under the CC BY-NC-ND 4.0 license.
