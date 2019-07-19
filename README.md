# Nasa Images
Simple app to showcase images from the [Nasa API](https://images.nasa.gov/docs/images.nasa.gov_api_docs.pdf)

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
  * Double click on the nasa_images_android_app/:app/Tasks/Verification/connectedDebugAndroidTest gradle task;
  * Results can be found in the *Build Output* window. They are also available under app/build/reports/androidTests/<some_other_folder_here>  (look for a file named index.html and open it with your browser);

* To run the app:
  * Connect an Android device with that is debug enabled and runs Android API level 28 or greater (or run an emulator with same configuration);
  * Select configuration "app" and tap on the green play button;
  
* Install the APK;
  * Optionally you can install the .apk file that is in the root directory of this repository. To do that, copy the file to your Android device running Android API level 28 or greater, tap to open it and follow the on-screen instructions. Make sure your device allows installation of .apk files from unknown sources.

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
* Google Material, RecyclerView, ConstraintLayout

   Chosen to build the UI, with a list and a collapsing toolbar with parallax;




