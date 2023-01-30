package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlin.time.Duration
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

private fun interval2(
    period: Duration, interval: Duration, initialDelay: Duration = Duration.ZERO, onFinish: () -> Unit
) = flow {
    delay(initialDelay)
    for (t in period.inWholeMilliseconds downTo 0 step interval.inWholeMilliseconds) {
        emit(Unit)
        delay(period)
    }
    onFinish()
}

private fun counter(period: Duration, initialDelay: Duration = Duration.ZERO) =
    generateSequence(0L, Long::inc).asFlow().onStart { delay(initialDelay) }.onEach { delay(period) }

private suspend fun main() = coroutineScope {
    interval(1.seconds).take(3).collect {
        println(it)
    }

    counter(1.seconds).take(3).collect {
        println(it)
    }
}
