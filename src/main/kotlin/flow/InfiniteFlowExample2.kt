package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take

private fun infiniteFlow(interval: Long = 1000) = generateSequence(0L, Long::inc).asFlow().onEach {
    delay(interval)
}

private suspend fun main() = coroutineScope {
    infiniteFlow().take(5).collect {
        println(it)
    }
}
