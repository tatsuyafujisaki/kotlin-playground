package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
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
 * Result:
 * Done!
 *
 * If `ensureActive()` inside coRunCatching is uncommented, the output will be:
 * onFailure: kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled
 */
private suspend fun main(): Unit = coroutineScope {
    val job = launch {
        coRunCatching {
            // Delays this coroutine a little so that it does not complete before being cancelled.
            delay(timeMillis = 100)
            println("launched!")
        }.onSuccess {
            println("onSuccess: $it")
        }.onFailure {
            println("onFailure: $it")
        }
    }
    job.cancelAndJoin()
    println("Done!")
}
