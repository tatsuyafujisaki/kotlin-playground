package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val mutableFlow = MutableSharedFlow<String>()
    val flow: Flow<String> = mutableFlow.asSharedFlow()

    launch {
        flow.collect {
            println("Collect: $it")
        }
    }

    // Purposefully get delayed to ensure that a collector starts
    // because the collector cannot collect a value emitted before the collector starts.
    delay(1000)
    mutableFlow.emit("a")
    mutableFlow.emit("a") // MutableSharedFlow can collect the same value as the previous one.
}
