package coroutine

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking {
    val job = launch {
        try {
            println("The job started!")
            delay(timeMillis = 10_000)
            println("The job ended!")
        } catch (e: Exception) {
            println(e)
        }
    }
    delay(timeMillis = 1) // waits a little bit or the job will be canceled even before it starts.
    job.cancelAndJoin()
    println("Done!")
}
