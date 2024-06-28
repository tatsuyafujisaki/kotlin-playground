package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private class MyCancellationException(t: CancellationException) : Exception(t)

private suspend fun main() = coroutineScope {
    val job = launch {
        try {
            println("try started!")
            delay(timeMillis = 10_000)
            println("try is ending!")
        } catch (e: CancellationException) {
            println("👀$e")

            // If you catch a CancellationException, rethrow it.
            // Don't throw a custom exception after catching a CancellationException.
            // throw MyCancellationException(e)

            throw e
        } catch (e: Exception) {
            println("👀$e")
        }
    }
    job.cancelAndJoin()
    println("job: $job")
}
