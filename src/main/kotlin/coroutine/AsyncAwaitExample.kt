package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

private suspend fun main() = coroutineScope {
    val deferred = async {
        try {
            println("try started!")
            delay(timeMillis = 1_000)
            println("try is ending!")
        } catch (e: CancellationException) {
            println("👀$e")
            throw e
        } catch (e: Exception) {
            println("👀$e")
        }
        "🍎"
    }
    deferred.cancel()
    val result = deferred.await()
    println("result: $result")
}
