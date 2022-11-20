package util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

object FlowUtil {
    fun <T> buildFlow(interval: Long = 1000, emitter: () -> T) = flow {
        while (true) {
            emit(emitter())
            delay(interval)
        }
    }

    fun timerFlow(interval: Long = 1000) = generateSequence(0L, Long::inc).asFlow().onEach {
        delay(interval)
    }
}
