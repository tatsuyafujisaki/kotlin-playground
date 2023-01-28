package flow

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take

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

private fun counter(period: Duration, initialDelay: Duration = Duration.ZERO) = generateSequence(0L, Long::inc)
    .asFlow()
    .onStart { delay(initialDelay) }
    .onEach { delay(period) }

private suspend fun main() = coroutineScope {
    interval(1.seconds).take(3).collect {
        println(it)
    }

    counter(1.seconds).take(3).collect {
        println(it)
    }
}
