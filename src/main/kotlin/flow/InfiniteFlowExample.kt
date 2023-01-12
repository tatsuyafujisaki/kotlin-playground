package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import java.time.LocalTime

private fun infiniteFlow1() = flow {
    while (true) {
        emit(LocalTime.now())
        delay(1000)
    }
}

private fun infiniteFlow2(interval: Long = 1000) = generateSequence(0L, Long::inc).asFlow().onEach {
    delay(interval)
}

private suspend fun main() = coroutineScope {
    infiniteFlow1().take(3).collect {
        println(it)
    }

    infiniteFlow2().take(3).collect {
        println(it)
    }
}
