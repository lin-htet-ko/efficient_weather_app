# Efficient Weather App

  Efficient Weather App have features to see the current weather information, the next five days predicate weathers information and search the weather information with city.

### Screenshots 📱
<img src="https://github.com/lin-htet-ko/efficient_weather_app/assets/68418303/fb39f4ad-f3c2-4482-b4e5-20f2023ea6b2" width=33%>
<img src="https://github.com/lin-htet-ko/efficient_weather_app/assets/68418303/b82941eb-a147-4ddb-928f-1f296793955a" width=33%>
<img src="https://github.com/lin-htet-ko/efficient_weather_app/assets/68418303/6f45c7da-c6b7-41a9-930a-0da25d018d7f" width=33%>

### Tech Stack 🍽️
  #### App Module
    - Jetpack Compose
    - Coil For Network Image Loading
    - Jetpack Navigation Component
    - Hilt For Dependency Injection
    - Google Location Service to get access location accurately with less Battery consuming 
    - Splash Api to support Splash Screen across various Android devices
  #### Network Module
    - Retrofit to establish http/https communications  
    - OkHttpClient to intercept our requests and responses, to see client-server communication logs
  #### Persistence Module
    - Room to support offline feature by caching

### App Architecture 🏗️
  In Efficient Weatehr App, We use <b>MVVM architecture</b> and some of custom components to reach our goals. The following screenshot is how our App Architecture works,
  
  <img src="https://github.com/lin-htet-ko/efficient_weather_app/assets/68418303/84d618d3-eac2-4d11-8eff-01ae33feed3d" width=80%>

## 
  The app is designed with package by layer for app module and it has 4 moudules. Each module have each responsibility. The app module is main module for App UI, the domain module is a mediator between app and data layer modules(:network, :persistence), the network module to connect the api server and our app, the persistence module to support caching mechanism when device is offline.
  For data layer modules, each module have Module class. That class's repository to export module's interface such as Manager class and some of utility classes instead of exporting classes directly. As an another important class, Manager class is responsible for creating dependencies and export interface dependencies to Module classes.

