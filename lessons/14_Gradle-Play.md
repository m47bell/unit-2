## Gradle and Publishing Apps

### Do Now (Morning)

Create a new blank Android app that includes a Fragment.

### Lesson (Morning)

#### Intro to Gradle

##### What is a a build system?

In order to turn the software that we write into something that can be run on a computer, we must build it. This includes compiling it (turning it into byte code) and linking it (combining different pieces of software, e.g. using a library with your new code). Build scripts can also be used to run tests.

##### What is Gradle?

Gradle is not the only way to build Android projects. Ant and Maven are other build systems available for Java and are commonly used for Android projects (especially Maven). Gradle is the default for Android Studio and is built on top of Groovy, which is a Ruby-like language built on the JVM.

`gradlew` is the Gradle Wrapper, which is its own script that determines how Gradle is run on the system.

### Exercises (Morning)

Use Gradle to do the following with your app:

> 1. Override the package name.

> 1. Change the midSdkVersion and targetSdkVersion. What's a reasonable minSdkVersion given that Fragments are used? What is the targetSdkVersion and what does that mean?

> 1. Change the compileSdkVersion to Jellybean.

> 1. Change the versionCode and versionName. Figure out [an appropriate version number](https://en.wikipedia.org/wiki/Point_release).

> 1. Bonus: Select an app you'll release to the Play Store and fix the Gradle files as above. [Proguard](http://developer.android.com/tools/help/proguard.html) your app.

### Pod Meetings (Afternoon)

### Lesson (Afternoon)

#### Publishing to the Play Store

##### Setting up Play Store Accounts

> Amy will lead this piece.

##### The Play Store Process

These are the steps for launching to the Google Play Store:

1. Login to Google Console.
1. Register the app.
1. Upload the required images/descriptions and the optional images/video.
1. Manage the versioning, uploading the apk and submit the updates/release notes.

> Exercise: Set up your Play account, choose an app that you've created, an begin the process of uploading the app to the Play store. If you choose an app from group project, coordinate with your team members so only one person uploads the app.

#### More Gradle

##### Libraries and Dependencies

An important part of gradle is manging the different artifacts that need to be compiled and run with your apps.

### Exercises (Afternoon)

> Exercise: Create an app using the [Picasso library](https://github.com/square/picasso).

### Assessment

[Exit Ticket](https://docs.google.com/a/c4q.nyc/forms/d/1a-gfjjsn35N-C6wrQU9y02vHoYLFaEfjUgD7J91n3rM/viewform)
