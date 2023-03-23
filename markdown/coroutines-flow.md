# Flow's `count()`, `toList()`, `drop()`, `take()`, `first()`, and `single()`
```kotlin
val flow = flowOf("a", "b", "c")

println(flow.count()) // 3
println(flow.count { it == "b" }) // 1
println(flow.toList()) // [a, b, c]
println(flow.drop(1).take(1).toList()) // [b]
println(flow.first()) // a
println(flow.single()) // IllegalArgumentException: Flow has more than one element
```

# How to convert `Flow<T?>` into `T` or `T?`
```kotlin
val x: String = flowOf(null, "a").filterNotNull().first()
val y: String? = flowOf(null, "a").firstOrNull()
```

# Four ways to update the value of `MutableStateFlow`
```kotlin
val _stateFlow = MutableStateFlow("")
val stateFlow = _stateFlow.asStateFlow()

_stateFlow.tryEmit("a")
println(stateFlow.value) // a

coroutineScope { _stateFlow.emit("b") }
println(stateFlow.value) // b

_stateFlow.update { "c" }
println(stateFlow.value) // c

_stateFlow.value = "d"
println(stateFlow.value) // d
```

# `StateFlow` is preferred over `SharedFlow`
> The recommended way to expose a Flow from a ViewModel is with a StateFlow

https://developer.android.com/codelabs/basic-android-kotlin-compose-update-data-room#2

> There are more types of flows, but this is what we recommend because we can optimize StateFlow very precisely.

https://youtu.be/fSB6_KE95bU?t=1001

# `repeatOnLifecycle()` is preferred over `asLiveData()` or `flowWithLifecycle()`

https://youtu.be/fSB6_KE95bU?t=610

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
