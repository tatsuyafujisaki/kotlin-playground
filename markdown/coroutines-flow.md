# [CancellationException](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-cancellation-exception/)

If you want to catch a `CancellationException`, rethrow it.

### References

> The same problem can be observed by catching a CancellationException and not rethrowing it:

> While catching Exception is an anti-pattern, this issue may surface in more subtle ways, like when using the runCatching function, which does not rethrow CancellationException.

https://kotlinlang.org/docs/cancellation-and-timeouts.html#cancellation-is-cooperative

> Using `runCatching` increases this risk of mis-handling cancellation. If you catch and don't rethrow all the `CancellationException`, your coroutines are not cancelled even if you cancel their `CoroutineScope`.
>
> This can very easily lead to:
> - unexpected crashes
> - extremely hard to diagnose bugs
> - memory leaks
> - performance issues
> - battery drain

https://detekt.dev/docs/rules/coroutines/#suspendfunswallowedcancellation

https://github.com/Kotlin/kotlinx.coroutines/issues/1814

https://stackoverflow.com/a/78683217/10867055

## How should you rethrow [CancellationException](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-cancellation-exception/)?

There are two options.

### Option 1: Use `throw`
### Option 2: Use [ensureActive](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/ensure-active.html)
  - Pros
    - https://betterprogramming.pub/the-silent-killer-thats-crashing-your-coroutines-9171d1e8f79b
    - https://github.com/Kotlin/kotlinx.coroutines/issues/3658#issuecomment-1465747377
    - https://github.com/Kotlin/kotlinx.coroutines/issues/1814#issuecomment-1943224856
    - https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
  - Cons
    - https://github.com/Kotlin/kotlinx.coroutines/issues/3658#issuecomment-1527096439

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

# Even with the `catch` operator, the backing Flow still terminates.

> Note: The catch operator only prevents the exception from crashing the app, the backing Flow still terminates. To
> resume collecting from the flow after the exception, consider the retry method.

# Actual time versus virtual time with `delay` in `runTest`

 runTest                                                                                             | Actual time to advance | Virtual time to advance 
-----------------------------------------------------------------------------------------------------|------------------------|-------------------------
 `delay(1_000)` not in `launch`                                                                      | 0s                     | 0s                      
 `delay(1_000)` in `launch`                                                                          | 0s                     | 1s                      
 `delay(1_000)` in a dispatcher that doesn't use `TestCoroutineScheduler` (e.g. using `withContext`) | 1s                     | 1s                      

# Difference among PublishSubject, BehaviorSubject, MutableSharedFlow, and MutableStateFlow

 &nbsp;                                               | PublishSubject<br>.create | BehaviorSubject<br>.create | BehaviorSubject<br>.createDefault | MutableSharedFlow | MutableSharedFlow<br>(replay=1) | MutableStateFlow 
------------------------------------------------------|---------------------------|----------------------------|-----------------------------------|-------------------|---------------------------------|------------------
 Requires a initial value                             | FALSE                     | FALSE                      | TRUE                              | FALSE             | FALSE                           | TRUE             
 Can collect the latest value when a collector starts | FALSE                     | TRUE                       | TRUE                              | FALSE             | TRUE                            | TRUE             
 Can emit the same value as the previous one          | TRUE                      | TRUE                       | TRUE                              | TRUE              | TRUE                            | FALSE            

> A SharedFlow is a highly-configurable generalization of StateFlow.

https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#sharedflow

# Cold stream versus hot stream (Cold flow versus hot flow)

 Cold stream                                                                              | Hot stream                                                                                                                                                
------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------
 is like an automatic faucet. It emits values only when a new consumer starts collecting. | is like a normal faucet. It emits values without a new consumer starts collecting.<br>is like a hot spring. It gushes out whether or not any user exists. 
 e.g. (regular) flow                                                                      | e.g. Channel, StateFlow, LiveData                                                                                                                         

The analogy for a hot stream is a hot spring. It gushes out whether a user exists.

# SupervisorJob

- If a child job throws an exception other than CancellationException, its parent and sibling jobs are NOT canceled.
    - i.e. Failure propagates downward only.

```
SupervisorJobA
 |        |
JobB    JobC
 |
JobD
```

 &nbsp; | If JobB is cancelled (i.e. throws CancellationException) ... | If JobB throws an exception other than CancellationException... 
--------|---------------------------------------------------------|-----------------------------------------------------------------
 JobA   | will NOT cancel.                                        | will cancel.                                                    
 JobC   | will cancel.                                            | will cancel.                                                    
 JobD   | will cancel.                                            | will cancel.                                                    
