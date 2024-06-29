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
            val deferred = async {
                println("async is running!")
            }
            deferred.await()
            println("try is ending!")
        },
        catchBlock = {
            println("catch started!")
            ensureActive() // throws CancellationException because the current coroutine is cancelled.
            println(it) // does not execute even if `ensureActive()` above is replaced by `throw t`.
            println("catch is ending!") // does not execute even if `ensureActive()` above is replaced by `throw t`.
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
 * async started!
 * catch started!
 * kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@c7ce68f
 * catch is ending!
 * Done!
 */
private suspend fun doExample2() = coroutineScope {
    val job = safeLaunch(
        tryBlock = {
            println("try started!")
            val deferred = async {
                // Delays this coroutine a little so that it does not complete before being cancelled.
                delay(timeMillis = 100)
                println("async is running!")
            }
            deferred.cancel()
            deferred.await()
            println("try is ending!")
        },
        catchBlock = {
            println("catch started!")
            println(it) // executes unless `ensureActive()` inside safeLaunch is replaced by `throw t`.
            println("catch is ending!") // executes unless `ensureActive()` inside safeLaunch is replaced by `throw t`.
        },
    )
    delay(timeMillis = 100) // waits for catchBlock to run.
    job.join()
    println("Done!")
}

private suspend fun main(): Unit = coroutineScope {
    doExample2()
}
