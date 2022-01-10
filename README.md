# TimeTable App

## ASJ-App Submission

### Problem Statement:

During my college journey, till now have heard from many students that they have faced this issue of remembering their college schedule, lecture timing and place. But when classes shifted to online they thought this issue they have been facing will be resolved, but it got more complex remembering and managing google meet links, remembering when you have classes and when you don't and even our college faculties faced this issue. </p>
Technology plays a vital role in day-to-day life activities which in turn made great changes in many work fields and out of the Mobile Application is one of the major developments, and taking that into consideration I built an android application for my college where students no longer need to remember their daily schedule whether the classes are offline or online and also can keep track of their assignments, exams schedule and any academic events.

### Problem Solution
 In a gist solution I thought was to build:</br>
 `An android app to help students of JECRC University to keep track of their timetables and upcoming academic events`</br>
 In this app students and faculties can add their academic schedule and upcoming events to the application, where they also get notification for their upcoming events 10 minutes before which will solve this comples and lesser thought problem very well.
#### Features: 
- Google Sign in with college email-id to stop spam.
- View academic timetable, upcoming evaluative component and details about classes and other events.
- Can add online meet link to it, so that you need not maintain it
- Notification reminder 10 minutes before each event

 Below are the working screenshots of the application:
 |Home Screen & TimeTable | Add Quiz/Assignment  | Add Course Timetable | Quiz/Assignment Screen
|:-----------------:|:--------------------:|:---------------------:|:---------------------:|
| <img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/6.jpg" height="400"/>|<img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/1.jpg" height="400"/>|<img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/3.jpg" height="400"/>| <img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/4.jpg" height="400"/>|

| Test Details | Course Details  | Logout
|:-----------------:|:--------------------:| :----------------------:|
| <img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/5.jpg" height="400"/>|<img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/7.jpg" height="400"/>| <img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/2.jpg" height="400"/>|

### Functionality and Concepts Used
- Room: App is completely offline, where user don't need to have an active internet connection to use the app.
- Architecture: Uses clean architecture to organize and maintain the code.
- User have complete flexibility on adding and removing the classes from schedule and editing it.
- Kotlin: Application is completely written (100%) in Kotlin
- Material Components: Uses material ui components to make app look more sleek, interactive and easy to use.
- Most of the Jetpack components are used here and have taken **Modern App Development Concepts**
  <img src="https://raw.githubusercontent.com/Iltwats/TimetableApp/master/assets/jetpack.png" height="300"/>
- Live Data and ViewModel has been used throughout the app to handle device rotation so that unsaved data doesn't get destroyed.

### Application Link 
You can download this app for demo from [here](https://github.com/Iltwats/TimetableApp/raw/master/app/build/outputs/apk/debug/app-debug.apk) or you can check the release section. This app allows login without JECRC college id.

### Future Scope
This app can be integrated with more features to solve problems daily faced by students such as to find last year papers/notes during exam time, a place where community events are shared and a place to share more of job opportunities and combinedly create a complete college app for students.




