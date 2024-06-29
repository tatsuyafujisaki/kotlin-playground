package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

inline fun CoroutineScope.safeLaunch(
    crossinline tryBlock: suspend CoroutineScope.() -> Unit,
    crossinline catchBlock: suspend CoroutineScope.(Throwable) -> Unit = {},
) = launch {
    try {
        tryBlock()
    } catch (t: Throwable) {
        ensureActive()
        catchBlock(t)
    }
}

/**
 * This example demonstrates the behavior described below.
 * > if the coroutine in which await was called got cancelled,
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * try started!
 * Done!
 */
private suspend fun doExample1() = coroutineScope {
    val job = safeLaunch(
        tryBlock = {
            println("try started!")
            delay(timeMillis = 100) // waits to avoid completing before being cancelled.
            println("try ended!")
        },
        catchBlock = {
            println("catch started!")
            // The following is not executed because `ensureActive()` inside safeLaunch throws CancellationException.
            println(it)
            println("catch ended!")
        },
    )
    job.cancel()
    delay(timeMillis = 100) // waits for catchBlock to run.
    job.join()
    println("Done!")
}

/**
 * This example demonstrates the behavior described below.
 * > or if the Deferred itself got completed with a CancellationException.
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * try started!
 * async started!
 * catch started!
 * kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@6575b4c7
 * catch ended!
 * Done!
 */
private suspend fun doExample2() = coroutineScope {
    val job = safeLaunch(
        tryBlock = {
            println("try started!")
            val deferred = async {
                println("async started!")
                delay(timeMillis = 100) // waits to avoid completing before being cancelled.
                println("async ended!")
            }
            deferred.cancel()
            deferred.await()
            println("try ended!")
        },
        catchBlock = {
            println("catch started!")
            // The following is not executed because `ensureActive()` inside safeLaunch does not throw CancellationException.
            // The following is executed unless you replace `ensureActive()` inside safeLaunch with `throw t`.
            println(it)
            println("catch ended!")
        },
    )
    delay(timeMillis = 100) // waits for catchBlock to run.
    job.join()
    println("Done!")
}

private suspend fun main() = coroutineScope {
    doExample1()
}
