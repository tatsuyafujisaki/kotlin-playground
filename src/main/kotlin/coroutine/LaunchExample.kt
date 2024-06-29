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
            delay(timeMillis = 100) // waits to avoid completing before being cancelled.
            println("try ended!")
        } catch (e: CancellationException) {
            println("catch started!")
            println(e)
            println("catch ended!")
            throw e
        }
    }
    job.cancelAndJoin()
    println("Done!")
}
