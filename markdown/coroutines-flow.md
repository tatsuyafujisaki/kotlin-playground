# Difference among PublishSubject, BehaviorSubject, MutableSharedFlow, and MutableStateFlow
&nbsp;|PublishSubject<br>.create|BehaviorSubject<br>.create|BehaviorSubject<br>.createDefault|MutableSharedFlow|MutableSharedFlow<br>(replay=1)|MutableStateFlow
--|--|--|--|--|--|--
Requires a initial value|FALSE|FALSE|TRUE|FALSE|FALSE|TRUE
Can collect the latest value when a collector starts|FALSE|TRUE|TRUE|FALSE|TRUE|TRUE
Can emit the same value as the previous one|TRUE|TRUE|TRUE|TRUE|TRUE|FALSE

> A SharedFlow is a highly-configurable generalization of StateFlow.

https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#sharedflow

# Cold stream versus hot stream
Cold stream|Hot stream
--|--
is like an automatic faucet.|is like a normal faucet.
is active only when any consumer exists.|is active whether or not any consumer exists.
emits values whenever a new consumer starts collecting.|emits values without a new consumer starts collecting.
e.g. (regular) flow|e.g. Channel, StateFlow, LiveData

# Coroutine
* is represented as Job.
* must be in a CoroutineScope.
* takes a suspending function as a driver. And coroutine is like a car.
* has two benefits:
  * Code using coroutines looks synchronously unlike code using callbacks.
  * Coroutines don't accidentally block the main thread.
* can create a new CoroutineScope in itself.
  * The new CoroutineScope inherits a CoroutineContext of the parent coroutine.
* can suspend on a thread and resume on another thread.
* can switch a thread it runs on.
* must be cancelled when it is not needed any more.
* must return before a suspending function, which runs on it, returns.
  * It's like a coroutine is omotenashi staff, and both the coroutine and the suspending function go to the toilet at the same time.

# CoroutineScope
* is a container of coroutines.
* takes a context where you can specify a dispatcher.
  * When a scope creates a coroutine, the coroutine becomes the child of the scope.
  * When a scope is cancelled, all the child coroutines are cancelled too.
* When one of the child coroutines fails, the entire scope fails.
* Coroutines and scopes must alternate or the app crashes.
* The first scope (like the Big Bang in the universe) is manually created by an Activity that implements the CoroutineScope interface.
* You can and should use a scope to cancel all running coroutines when the user navigates away from an Activity or a Fragment.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html

# coroutineScope (Not CoroutineScope)
* is a suspending function that creates a Coroutine Scope and runs a given block as a suspending function as well as an extension function to the CoroutineScope, and get the result.
* waits for all the child coroutines to complete.
* will cancel whenever any of its children fail. It's different from supervisorScope.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/coroutine-scope.html

# supervisorScope
* wait for child coroutines to complete like coroutineScope.
* won’t cancel other children when one of them fails. It's different from coroutineScope.

# CoroutineContext
* is key-value pairs.
  * "Dispatcher" = "Main"
  * "Job" = "Coroutine A"
* can be combined with another CoroutineContext using '+'.
  * The right operand takes precedence if the left operand and the right operand have different values for the same key,
* whose main content is a dispatcher.

# CoroutineContext.Element
* is a context that has only one element (key-value pair).
* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/-element/index.html

# ViewModel.viewModelScope
* is a scope.
* is an extension on ViewModel from the KTX library.
* automatically cancels its child coroutines to avoid leaking memory when the ViewModel is destroyed.
* is a default dispatcher of Dispatchers.Main.

# CoroutineDispatcher
* specify a thread where a coroutine runs.
* knows how to resume suspended coroutines in it.
* is a CoroutineContext.Element where the key is "Dispatcher" and the value is something like "Dispatchers.Default".
* has three variants.
  * Dispatchers.Default
    * is the default.
    * is a pool of threads to do CPU-intensive work off the main thread.
    * The minimum number of threads is 2.
    * The maximum number of threads is the number of CPU cores.
  * Dispatchers.Main
    * is the main thread on Android.
    * is to update UI and MutualLiveData.
      * The app crashes by design if you try to update MutualLiveData in Dispatchers.Default or Dispatchers.IO.
  * Dispatchers.IO
    * is a pool of threads to do:
      * network I/O
      * database I/O
      * file I/O
* Dispatchers.Default and Dispatchers.IO share the same threads to avoid thread switches.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/

# withContext
* is a suspending function that switches a context (i.e. thread) instead of creating a new coroutine like coroutine builders do.
* A good practice is to use withContext to make sure every function is safe to be called on any Dispatcher.
  * That way, the caller never has to think about what thread will be needed to execute the function.

# runBlocking
* is the same as coroutineScope but is a non-suspending function and blocks the current thread.
* is a function that creates a CroutineScope and runs a given block as a suspending function as well as an extension function to the CoroutineScope, and get the result.
* should be used in "main" function and NOT from another coroutine.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html

# Deferred
* is a Job that immediately returns a result as a reminder.
* is like you get a waiting number ticket at a fast food restaurant and will be called once the coroutine is completed and the result is ready.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/
* Deferred.await()
  * is a suspending function.
  * is used to wait for the result from a coroutine started with the async builder.

# Job
* is a cancelable unit of work.
* is a coroutine in source code.
* can be created by a coroutine builder like `launch()` or `Job()`.
* implements `CoroutineContext.Element`, which is a key-value pair containing "Job = coroutine A".
* has a parent job and zero or more child jobs.
* Cancelling a parent job cancels all the child jobs.
* `Job.join()` waits for the job to complete.

# Suspending function
* is a function that can suspend a coroutine without blocking the thread.
* can run only inside a coroutine or another suspending function.

# Coroutine builder
* creates a coroutine.
* is an extension function of CoroutineScope meaning a coroutine builder must be used inside a CoroutineScope.
* is where you can start a coroutine and run a given lambda in it.
* is where you can start a coroutine and run a suspending function in it.

## launch
* returns immediately, but does not return the result (fire-and-forget).
* starts a coroutine and returns the coroutine as a job on which you can use "join" if you want to wait for the job to complete.
* A lambda inside launch{} is automatically marked with "suspend".
* When creating a coroutine from a non-coroutine, start with `launch`.

## async
* returns the future result as a Deferred on which you must await().
* A big difference between launch and async is how they handle exceptions. async returns an exception instead of throwing it.

# Structured concurrency
* is to avoid a leaked coroutine.
* guarantees that when a suspending function returns, all of its work is done.
* guarantees that when a coroutine errors, its caller or scope is notified.
* guarantees the three things:
  * When a scope cancels, all of its coroutines cancel.
  * When a suspend function returns, all of its work is done.
  * When a coroutine errors, its caller or scope is notified.

# Leaked coroutine
* occurs when you lose track of a coroutine.
* can waste memory, CPU, disk, or even make an unnecessary network request.

# yield()
* forcibly start child jobs. If yield() is not called before child.cancel(), the child job may not start by the time child.cancel() is called, so the "finally" block won't be executed when child.cancel() is called.
* https://kotlinlang.org/docs/reference/coroutines/exception-handling.html#cancellation-and-exceptions

# Continuation
* is a callback in Kotlin's speak.
  * https://www.youtube.com/watch?v=ZTDXo0-SKuU

# Cancel propagation
* job.cancel() throws CancellationException.
* Cancellation means throwing CancellationException.
* If a parent job calls cancel(), its children will be canceled too. On the other hand, if a child job calls cancel(), its parent job and sibling jobs will NOT be canceled.
  * i.e. Cancelation is propagated only downwards.
* If a child job throws any exception other than CancellationException, its parent job and sibling jobs will be canceled too.
  * i.e. Failure is propagated bidirectionally.
  * By default, a failure of any of the job’s children leads to an immediate failure of its parent and cancellation of the rest of its children. This behavior can be customized using SupervisorJob.
    * https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html
* Failure means throwing any exception other than CancellationException.

# SupervisorJob
* If a child job throws any exception other than CancellationException, its parent job and sibling jobs will NOT be canceled.
* i.e. Failure is propagated only dowards.

```
SupervisorJobA
 |        |
JobB    JobC
 |
JobD
```

&nbsp;|If JobB cancels (i.e. throws CancellationException) ...|If JobB throws an exception other than CancellationException...
--|--|--
JobA|will NOT cancel.|will cancel.
JobC|will cancel.|will cancel.
JobD|will cancel.|will cancel.
