package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive

/**
 * The coRunCatching implementation
 * https://detekt.dev/docs/rules/coroutines/#suspendfunswallowedcancellation
 */
inline fun <T> CoroutineScope.coRunCatching(block: CoroutineScope.() -> T): Result<T> {
    return try {
        Result.success(value = block())
    } catch (e: Throwable) {
        ensureActive()
        Result.failure(exception = e)
    }
}

/**
 * Result:
 * try started!
 * job: StandaloneCoroutine{Cancelled}@36412113
 */
private suspend fun main(): Unit = coroutineScope {
    coRunCatching {
        val job = async {
            // Delay this coroutine a little so that it does not complete before being cancelled.
            delay(timeMillis = 1_000)
            println("launched!")
        }
        job.cancel()
        job.await()
        println("job: $job")
    }.onSuccess {
        println("onSuccess: $it")
    }.onFailure {
        println("onFailure: $it")
    }
}
