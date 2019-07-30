# Nasa Images
Simple app to showcase images from the [Nasa API](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf)

Visual identity inspired by the [Nasa Web Design System](https://nasa.github.io/nasawds-site/components/colors/)

----

### User instructions

* Use the search bar to query specific images from Nasa;
* To see detailed information, tap on the image. From there, you can see the image in full-screen by turning your device to landscape. Pinch or double-tap to zoom;

<img width="200" alt="device-2019-07-20-024442" src="https://user-images.githubusercontent.com/6198387/61574628-b03c3f00-aa98-11e9-919b-87d528635745.png"><img width="200" alt="device-2019-07-20-024548" src="https://user-images.githubusercontent.com/6198387/61574629-b03c3f00-aa98-11e9-85eb-9479f5f52278.png"><img width="200" alt="device-2019-07-20-024622" src="https://user-images.githubusercontent.com/6198387/61574630-b03c3f00-aa98-11e9-914a-85183feac9ba.png">

-----

### How to build & run

* Open Android Studio 3.4.1 and select file -> new -> Import Project...
* Select the root folder of this repository;
* Wait for Android Studio to sync gradle files and setup configurations;


* To run the unit tests:
  * Double click on the nasa_images_android_app/:app/Tasks/Verification/testDebugUnitTest gradle task;
  * Results can be found in the *Build Output* window. They are also available under app/build/reports/tests/testDebugUnitTest/index.html  (open it with your browser);
  
* To run instrumentation tests:
  * Connect an Android device with that is debug enabled and runs Android API level 28 or greater (or run an emulator with same configuration);
  * In your device, under configuration -> Developer Options -> Turn off 'Window animation scale', 'Transition animation scale', and 'Animator duration scale';  
  * Double click on the nasa_images_android_app/:app/Tasks/Verification/connectedDebugAndroidTest gradle task;
  * Results can be found in the *Build Output* window. They are also available under app/build/reports/androidTests/<some_other_folder_here>  (look for a file named index.html and open it with your browser);

       <img width="400" alt="Screen Shot 2019-07-19 at 16 46 17" src="https://user-images.githubusercontent.com/6198387/61562664-2c5a6680-aa48-11e9-97c1-160e7c8b84c8.png">

---
 For the following options, make sure the animations are ON:
 
In your Android device, under configuration -> Developer Options -> Set 'Window animation scale', 'Transition animation scale', and 'Animator duration scale' to 1x; 

* To run the app:
  * Connect an Android device that is debug enabled and runs Android API level 28 or greater (or run an emulator with same configuration); 
  * In the Android Studio, select configuration "app" and tap on the green play button;
  
* Install the APK;
  * Optionally you can install the .apk file that is in the root directory of this repository. To do that, copy the file to your Android device running Android API level 28 or greater, tap to open it and follow the on-screen instructions. Make sure your device allows installation of .apk files from unknown sources.

---
### App Architecture Overview

App uses Model View Presenter Architecture

<img width="823" alt="Screen Shot 2019-07-19 at 17 04 32" src="https://user-images.githubusercontent.com/6198387/61562598-092fb700-aa48-11e9-8f4b-2b6aa548a701.png">

When a search is made, the query is sent to the Presenter, which calls the Repository, which calls the NasaApi, which returns to the Repository an 'order' that must be fulfilled. The Repository uses a Mapper to transform the stream of objects returned by the 'order', and returns to the Presenter a 'modified order'. The Presenter executes the order and notifies the view whenever the status of the order changes. The view, then, displays the status to the user accordingly, being that an error, a collection of images, or a loading indicator.

---
### Libraries Used

* [Mockito](https://site.mockito.org/)

   Chosen because it allows stubbing of methods and classes and has flexible verification methods, which makes it easy to write tests;
* [Espresso](https://developer.android.com/training/testing/espresso)

   Chosen for instrumentation tests because of its human-readable code;
* [Gson](https://github.com/google/gson)

   Chosen because of its integration with Retrofit to deserialize objects;
* [Retrofit](https://square.github.io/retrofit/)

   Chosen because of its conciseness, turning an HTTP API into a Java/Kotlin interface. Integrates with RxJava;
* [Dagger](https://github.com/google/dagger)

   Chosen dependency injection framework to modularize classes and components;
* [RxJava](https://github.com/ReactiveX/RxJava)

   Chosen to ease development because it allows declarativeness and reactiveness. Easy to compose sequences, transform streams, and handle work among threads;
* [OkHttp](https://square.github.io/okhttp/)

   Chosen as HTTP client because of its efficiency, logger, integration with retrofit, and out-of-the-box handling of network connection problems;
* [Glide](https://github.com/bumptech/glide)

   To load images from a local or remote address. Chosen because of its focus on making scrolling of an image list smooth and fast;
* [PhotoView](https://github.com/chrisbanes/PhotoView)

   Chosen because of its out-of-the-box zooming capability;
* Google Material, RecyclerView, ConstraintLayout, AndroidX

   Chosen to work with the Android Framework components, build the UI, including a list and a collapsing toolbar with parallax;




