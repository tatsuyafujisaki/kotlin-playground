package coroutine

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

/**
 * https://stackoverflow.com/a/78683217/10867055
 */
private suspend fun main() = coroutineScope {
    val job = launch {
        repeat(5) { i ->
            try {
                println("job: I'm sleeping $i ...")
                delay(100)
            } catch (e: CancellationException) {
                println(e)
                throw e
            }
        }
    }
    delay(150)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}
