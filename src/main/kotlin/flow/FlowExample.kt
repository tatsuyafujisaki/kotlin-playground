package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import java.time.LocalTime

private fun infiniteFlow() = flow {
    while (true) {
        emit(LocalTime.now())
        delay(1000)
    }
}

private suspend fun main(): Unit = coroutineScope {
    infiniteFlow().take(5).collect {
        println(it)
    }
}
