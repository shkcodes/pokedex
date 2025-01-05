I have built the app using the Pokemon API as required. I used Compose for UI instead of views as it is a fresh project so it makes sense to use the latest tools.
I tried focusing on meeting all the requirements:
* Kotlin
* List/detail screen
* MVVM
* Unit tests
* UI tests
* DI using Hilt
* Coil for image loading

Other notable libraries I used were:
* MockK for mocking
* Kotest for assertions
* Kotlin serialization to facilitate type safe navigation
* Moshi and Retrofit for API calls
* Turbine to test flows
  
The things I would focus next on are:
* Modularizing using convention plugins
* Fleshing out the detail screen (and possibly list screen) UI
* Adding offline support

These are the things off the top of my head. The project's priorities would help us in deciding what to tackle next.
