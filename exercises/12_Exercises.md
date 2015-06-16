### Exercises

Create an app that implements sleep sort.

1. Create an app that allows the user to input five integers.
2. When the user presses the "sort" button, the app should sort the numbers and then display the list of sorted numbers. Do this using a built-in `sort()` method.
3. Refactor the code so that the `sort()` is called in a separate method (instead of `onCreate` or wherever you are working).
4. Instead of using the `sort()` method, implement sleep sort. The main idea of sleep sort is that for each element, create a new thread that sleeps for $n_i seconds. When the thread is done, you can make it the next element of the new array. For example in:
```
1 5 2 8 2
```
You have 5 threads that each sleep for 1, 5, 2, 8 and 2 seconds, all at the same time. After 4 seconds your array looks like
```
1 2 2
```
After 8 seconds your array looks like
```
1 2 2 5 8
```

#### Bonus

* Have each number show on the screen as it finishes sleeping.

* Still using the sleep sort idea, figure out a way to make it faster.

* Have the app display a notification when the sleep sort is done.

#### Assessment

[Exit Ticket](https://docs.google.com/forms/d/1a-gfjjsn35N-C6wrQU9y02vHoYLFaEfjUgD7J91n3rM/viewform?usp=send_form)
