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
