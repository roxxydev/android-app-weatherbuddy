Weather Buddy
===========

Screenshots

![Alt text](/screenshots/Screenshot_a.png?raw=true "")
![Alt text](/screenshots/Screenshot_b.png?raw=true "")

* Weather Buddy let user know the current weather forecast of his current location.
* The application uses OpenWeatherMap to get the weather forecast base on user device geolocation.
* On launch of application, it will obtain user's current location with latitude and longitude obtained
from location provider of the device

* To build the project, run the command
```groovy
gradle clean assembleDebug
```

* To build the project and automatically install to device connected, run the command
```groovy
gradle assembleDebug installDebug
```

* The command will generate the apk file under app/build/outputs/apk

* Project is separated into two modules. The first one the the `core` module which contains the entity classes representing the JSON structure object, the http calls. The second module is the `app` which contains the application. The app module implements the core project library.

* Third party libraries have been used, namely: Picasso for image loading, Retrofit for http calls, Robospice for making any http client asynchronous and thread-safe, and GSON for converting JSON String to Java objects or vice-versa.

* AppCompat support library of android have been used for supporting ActionBar to a wide-range of Android versions and provide APIs to lower versions.
