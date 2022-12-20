package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private suspend fun main(): Unit = coroutineScope {
    val mutableFlow = MutableSharedFlow<String>()
    val flow: Flow<String> = mutableFlow

    launch {
        flow
            .map {
                if (it == "b") throw Throwable() else it
            }.catch {
                println("Catch: $it")
                // Prevent the flow from completing.
                emitAll(flow)
            }.collect {
                println("Collect: $it")
            }
    }

    delay(1000)
    mutableFlow.emit("a")
    mutableFlow.emit("b")
    delay(1000)
    mutableFlow.emit("c")
}
