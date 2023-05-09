package flow

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take

/**
 * Notice the order of [delay] and [FlowCollector.emit]. i.e. The initial delay is the same as the periodic delay.
 */
private fun interval(period: Duration) = flow {
    while (true) {
        delay(period)
        emit(Unit)
    }
}

private fun interval(period: Duration, delay: Duration = Duration.ZERO) = flow {
    delay(delay)
    while (true) {
        emit(Unit)
        delay(period)
    }
}

private fun countUp(period: Duration, delay: Duration = period) =
    generateSequence(0, Long::inc).asFlow().onStart { delay(delay) }.onEach { delay(period) }

private fun countDown(
    period: Duration,
    interval: Duration,
    delay: Duration = Duration.ZERO
) = flow {
    delay(delay)
    for (t in period.inWholeSeconds downTo 0 step interval.inWholeSeconds) {
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
        period = 3.seconds,
        interval = 1.seconds
    ).onCompletion { println("onCompletion") }
        .collect {
            println("collect: $it")
        }
}
