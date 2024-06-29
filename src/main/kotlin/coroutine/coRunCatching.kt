package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/**
 * https://detekt.dev/docs/rules/coroutines/#suspendfunswallowedcancellation
 */
inline fun <T> CoroutineScope.coRunCatching(block: CoroutineScope.() -> T): Result<T> {
    return try {
        Result.success(value = block())
    } catch (exception: Throwable) {
        ensureActive()
        Result.failure(exception = exception)
    }
}

/**
 * This example demonstrates the behavior described below.
 * > if the coroutine in which await was called got cancelled,
 * https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html
 *
 * Result:
 * try started!
 * async started!
 * onFailure: kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@64db3069
 * Done!
 */
private suspend fun doExample1() = coroutineScope {
    val job = launch {
        coRunCatching {
            println("try started!")
            delay(timeMillis = 100) // waits to avoid completing before being cancelled.
            println("try ended!")
        }.onSuccess {
            println("onSuccess: $it")
        }.onFailure {
            // The following is not executed because `ensureActive()` inside coRunCatching throws CancellationException.
            println("onFailure: $it")
        }
    }
    job.cancel()
    delay(timeMillis = 100) // waits for catch inside coRunCatching to run.
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
 * onFailure: kotlinx.coroutines.JobCancellationException: DeferredCoroutine was cancelled; job=DeferredCoroutine{Cancelled}@1c5f44ad
 * Done!
 */
private suspend fun doExample2() = coroutineScope {
    coRunCatching {
        println("try started!")
        val deferred = async {
            println("async started!")
            delay(timeMillis = 100) // waits to avoid completing before being cancelled.
            println("async ended!")
        }
        deferred.cancel()
        deferred.await() // throws CancellationException.
        println("try ended!")
    }.onSuccess {
        println("onSuccess: $it")
    }.onFailure {
        // The following is executed because `ensureActive()` inside coRunCatching does not throw CancellationException.
        println("onFailure: $it")
    }
    println("Done!")
}

private suspend fun main() = coroutineScope {
    doExample1()
}
