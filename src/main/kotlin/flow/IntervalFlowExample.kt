package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

private fun interval(period: Duration, initialDelay: Duration = Duration.ZERO) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}

private fun countUp(period: Duration, initialDelay: Duration = Duration.ZERO) =
    generateSequence(0, Long::inc).asFlow().onStart { delay(initialDelay) }.onEach { delay(period) }

private fun countDown(
    period: Long, interval: Long, initialDelay: Duration = Duration.ZERO
) = flow {
    delay(initialDelay)
    for (t in period downTo 0 step interval) {
        emit(t)
        delay(interval)
    }
}

private suspend fun main() = coroutineScope {
    interval(1.seconds).take(3).collect {
        println("collect: $it")
    }

    countUp(1.seconds).take(3).collect {
        println("collect: $it")
    }

    countDown(
        period = 3_000,
        interval = 1_000
    ).onCompletion { println("onCompletion") }
        .collect {
            println("collect: $it")
        }
}
