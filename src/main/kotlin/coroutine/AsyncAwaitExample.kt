package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/**
 * This example demonstrates the behavior described below.
 * > if the coroutine in which await was called got cancelled,
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * try started!
 * async is running!
 * catch started!
 * Done!
 */
private suspend fun doExample1() = coroutineScope {
    val job = launch {
        val deferred = async {
            println("async is running!")
        }
        try {
            println("try started!")
            deferred.await()
            println("try is ending!")
        } catch (e: CancellationException) {
            println("catch started!")
            ensureActive() // throws CancellationException because the current coroutine was cancelled.
            println(e) // does not execute even if `ensureActive()` above is replaced by `throw e`.
            println("catch is ending!") // does not execute even if `ensureActive()` above is replaced by `throw e`.
        }
    }
    job.cancelAndJoin()
    println("Done!")
}

/**
 * This example demonstrates the behavior described below.
 * > or if the Deferred itself got completed with a CancellationException.
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * async started!
 * try started!
 * catch started!
 * kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@7919012c
 * catch is ending!
 * Done!
 */
private suspend fun doExample2() = coroutineScope {
    val deferred = async {
        println("async started!")
        // Delay this coroutine a little so that it does not complete before being cancelled.
        delay(100)
        println("async is ending!")
    }
    deferred.cancel()
    try {
        println("try started!")
        deferred.await()
        println("try is ending!")
    } catch (e: CancellationException) {
        println("catch started!")
        ensureActive() // does not throw CancellationException because the current coroutine was cancelled.
        println(e) // executes unless `ensureActive()` above is replaced by `throw e`.
        println("catch is ending!") // executes unless `ensureActive()` above is replaced by `throw e`.

    }
    println("Done!")
}

private suspend fun main() = coroutineScope {
    doExample1()
}
