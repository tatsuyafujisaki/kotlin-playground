# Flow's `count()`, `toList()`, `drop()`, `take()`, `first()`, and `single()`
```kotlin
val flow = flowOf("a", "b", "c")

println(flow.count()) // 3
println(flow.toList()) // [a, b, c]
println(flow.drop(1).take(1).toList()) // [b]
println(flow.first()) // a
println(flow.single()) // IllegalArgumentException: Flow has more than one element
```

# Actual time versus virtual time with `delay` in `runTest`
runTest|Actual time to advance|Virtual time to advance
--|--|--
`delay(1_000)` not in `launch`|0s|0s
`delay(1_000)` in `launch`|0s|1s
`delay(1_000)` in a dispatcher that doesn't use `TestCoroutineScheduler` (e.g. using `withContext`)|1s|1s

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

# `yield()`
* forcibly start child jobs. If `yield()` is not called before `child.cancel()`, the child job may not start by the time `child.cancel()` is called, so the `finally` block won't be executed when `child.cancel()` is called.
* https://kotlinlang.org/docs/exception-handling.html#cancellation-and-exceptions

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
