package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

inline fun CoroutineScope.safeLaunch(
    crossinline tryBlock: suspend CoroutineScope.() -> Unit,
    crossinline catchBlock: CoroutineScope.(Throwable) -> Unit = {},
) = launch {
    try {
        tryBlock()
    } catch (t: Throwable) {
        ensureActive()
        catchBlock(t)
    }
}

/**
 * Result:
 * try started!
 * Done!
 *
 * If `ensureActive()` inside safeLaunch is uncommented, the output will be:
 * try started!
 * catch started!
 * kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@10b714fe
 * catch ended!
 * Done!
 */
private suspend fun main(): Unit = coroutineScope {
    val job = safeLaunch(
        tryBlock = {
            println("try started!")
            // Delays this coroutine a little so that it does not complete before being cancelled.
            delay(timeMillis = 100)
            println("try is ending!")
        },
        catchBlock = {
            println("catch started!")
            println(it)
            println("catch ended!")
        }
    )
    job.cancelAndJoin()
    println("Done!")
}
