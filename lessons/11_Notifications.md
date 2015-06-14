## Notifications

### Objective

Students will become familiar with the Android notification system and practice implementing their own complex
notifications.

### Do Now (Morning)

Create a timer app. The app should take in some number of seconds before a "reminder" comes up. Use `handler.PostDelayed` and open a new activity (can just be an activity that displays "New Activity") when the countdown has finished.

### Lesson (Morning)

#### Notifications in Android

Notifications are tricky because they are displayed outside of your application. Then notification drawer and notifications are controlled by the Android system.

To trigger a notification, call `.notify()` with a `Notification` object on the `NotificationManager` object.

#### Notification Builder

In order to create a `Notification` object, use the [`NotificationCompat.Builder`](http://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html). The notification information can be set up on the `Builder` object, and then the notification can be created by calling `.build()`. The Builder pattern is a common Android pattern, so that you create immutable objects.

A Notification must include `setSmallIcon()`, `setContentTitle()` and `setContentText()`.

### Exercises (Morning)

Using the app from the Do Now, have the application issue a notification when it's time for the reminder.

### Do Now (Afternoon)

Modify your Do Now app so that when the user clicks on the notification, it opens up the new activity.

### Lesson (Afternoon)

#### Actions

A notification can trigger a variety of different actions. To set the Activity that is opened, create a `PendingIntent` object. This object can then be passed to the `NotificationCompat.Builder` object with several different methods. Use the `setContentIntent()` method to set the Activity that gets called when the user clicks on the notification.

##### PendingIntent

The `PendingIntent` object can be created using `PendingIntent.getActivity()`, `PendingIntent.getActivities()`, `PendingIntent.getBroadcast()` or `PendingIntent.getService()`. These methods take in an Intent object and Application Context.

#### Lock Screen

Notifications can be displayed on the lock screen. Using `setVisibility()` you can display the full notification on the lock screen using `VISIBILITY_PUBLIC`, not show the notification on the lock screen using `VISIBILITY_SECRET` and only show limited information using `VISIBILITY_PRIVATE`. If using a private notifcation, you can set up the lock screen version using `setPublicVersion()`.

#### Updating and Removing

If a notification may need to be updated, use a notification ID when calling `.notify()`. You can then use this notification ID to call `.notify()` with the notification ID and the new Notification object. You can also use this notification ID to `cancel()` the notification.

#### Expanded Notifications

Notifications can be expanded to a larger view so that you can see more detailed info in the notification drawer. In order to expand notifications, you can use `NotificationCompat.Builder.setStyle()`, which takes in a layout that can be displayed in the expanded view.

#### User Experience

Notifcations have strict design guidelines since they appear outside the application. When creating notifications, be sure to look at the [notifications design guide](http://developer.android.com/design/patterns/notifications.html). Some other things to keep in mind is that users may turn off notifications or even uninstall an app; be mindful of how you use notifications in your app.

### Exercises (Afternoon)

Fork this [Meme Project](https://github.com/MadelynTav/MemeProject). If you already have a fork of this project, work off a new branch. Add a feature that uploads the image to Dropbox in the background and sends you a notification when it's done.

Lets start with pretending to upload and get the notifications correct, then try to integrate Dropbox, time permitting.  For future reference, when you see "Oauth", prepare to spend some time on it.

[Dropbox Api](https://www.dropbox.com/developers/core/start/android)

### Assessment

[Exit ticket](https://docs.google.com/forms/d/1a-gfjjsn35N-C6wrQU9y02vHoYLFaEfjUgD7J91n3rM/viewform?usp=send_form)

### Resources

[Android Asset Studio](https://romannurik.github.io/AndroidAssetStudio/) 
