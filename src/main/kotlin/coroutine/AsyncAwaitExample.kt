package coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive

private suspend fun CoroutineScope.doExample1() {
    val deferred = async {
        println("async started!")
        // Delay this coroutine a little so that it does not complete before being cancelled.
        delay(100)
        println("async is ending!")
        throw CancellationException()
    }
    try {
        println("try started!")
        deferred.await()
        println("try is ending!")
    } catch (e: CancellationException) {
        println("catch started!")
        currentCoroutineContext().ensureActive() // throws if the current coroutine was cancelled
        println(e) // if this line executes, the exception is the result of `await` itself
        println("catch is ending!")
    }
    println("Done!")
}

private suspend fun CoroutineScope.doExample2() {
    val deferred = async {
        println("async started!")
        // Delay this coroutine a little so that it does not complete before being cancelled.
        delay(100)
        println("async is ending!")
    }
    deferred.cancel()
    try {
        deferred.await()
        println("try is ending!")
    } catch (e: CancellationException) {
        println("catch started!")
        currentCoroutineContext().ensureActive() // throws if the current coroutine was cancelled
        println(e) // if this line executes, the exception is the result of `await` itself
        println("catch is ending!")
    }
    println("Done!")
}

private suspend fun main() = coroutineScope {
    doExample1()
}
