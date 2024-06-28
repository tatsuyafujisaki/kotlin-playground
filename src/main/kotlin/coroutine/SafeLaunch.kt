package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

fun CoroutineScope.safeLaunch(
    handleException: CoroutineScope.(Exception) -> Unit = {},
    handleCancellationException: CoroutineScope.(CancellationException) -> Unit = handleException,
    tryBlock: suspend CoroutineScope.() -> Unit,
): Job = launch {
    try {
        tryBlock()
    } catch (e: CancellationException) {
        handleCancellationException(e)
        throw e
    } catch (e: Exception) {
        handleException(e)
    }
}

private suspend fun main() = coroutineScope {
    val job = safeLaunch(
        handleException = { println("👀$it") },
    ) {
        println("try started!")
        delay(timeMillis = 10_000)
        println("try is ending!")
    }
    job.cancelAndJoin()
    println("job: $job")
}
