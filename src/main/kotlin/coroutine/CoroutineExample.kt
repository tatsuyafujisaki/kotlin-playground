package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val job = launch {
        try {
            println("The job started!")
            delay(timeMillis = 10_000)
            println("The job ended!")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            println(e)
        }
    }
    delay(timeMillis = 1) // waits a little bit or the job will be canceled even before it starts.
    job.cancelAndJoin()
    println("Done!")
}
