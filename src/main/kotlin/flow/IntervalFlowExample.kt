package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * Usage: interval(1.seconds)
 */
private fun interval(period: Duration, initialDelay: Duration = Duration.ZERO) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}

private fun countUp(period: Duration, initialDelay: Duration = Duration.ZERO) =
    generateSequence(0L, Long::inc).asFlow().onStart { delay(initialDelay) }.onEach { delay(period) }

private fun countDown(
    millisInFuture: Long, countDownInterval: Long, initialDelay: Duration = Duration.ZERO, onFinish: () -> Unit
) = flow {
    delay(initialDelay)
    for (t in millisInFuture downTo 0 step countDownInterval) {
        emit(t)
        delay(countDownInterval)
    }
    onFinish()
}

private suspend fun main() = coroutineScope {
    interval(1.seconds).take(3).collect {
        println("collect $it")
    }

    countUp(1.seconds).take(3).collect {
        println("collect $it")
    }

    countDown(
        millisInFuture = 10.seconds.inWholeMilliseconds, countDownInterval = 500.milliseconds.inWholeMilliseconds
    ) { println("onFinish()") }.collect {
        println("collect: $it")
    }
}
