package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(
    handleException: CoroutineScope.(Exception) -> Unit = {},
    tryBlock: suspend CoroutineScope.() -> Unit,
) = launch {
    try {
        tryBlock()
    } catch (e: Exception) {
        ensureActive()
        handleException(e)
    }
}

/**
 * Result:
 * try started!
 * job: StandaloneCoroutine{Cancelled}@36412113
 */
private suspend fun main() = coroutineScope {
    val job = safeLaunch(
        handleException = { println(it) },
    ) {
        println("try started!")
        delay(timeMillis = 100)
        println("try is ending!")
    }
    job.cancelAndJoin()
    println("job: $job")
}
