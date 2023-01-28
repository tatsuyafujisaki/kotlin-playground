package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val mutableFlow = MutableStateFlow("a")
    val flow: Flow<String> = mutableFlow.asSharedFlow()

    launch {
        flow.collect {
            println("Collect: $it")
        }
    }

    // No need to get delayed to ensure that a collector starts
    // because the collector can collect a value emitted before the collector starts.
    mutableFlow.emit("a")

    // This will not be collected because, unlike MutableSharedFlow, MutableStateFlow CANNOT collect the same value.
    mutableFlow.emit("a")
}
