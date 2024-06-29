package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val job = launch {
        try {
            println("try started!")
            delay(timeMillis = 10_000)
            println("try is ending!")
        } catch (e: CancellationException) {
            println(e)
            throw e
        } catch (e: Exception) {
            println(e)
        }
    }
    job.cancelAndJoin()
    println("job: $job")
}
