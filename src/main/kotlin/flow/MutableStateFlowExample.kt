package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private suspend fun mutableStateFlowExample(): Unit = coroutineScope {
    val mutableStateFlow = MutableStateFlow("a")
    val stateFlow: StateFlow<String> = mutableStateFlow

    launch {
        stateFlow.collect {
            println("Collect: $it")
        }
    }

    // No need to get delayed to ensure that a collector starts
    // because the collector can collect a value emitted before the collector starts.
    mutableStateFlow.emit("a")
    println("a is emitted.")
    mutableStateFlow.emit("a")
    println("a is emitted, but unlike MutableSharedFlow, MutableStateFlow CANNOT collect the same value.")
}

private suspend fun main(): Unit = coroutineScope {
    mutableStateFlowExample()
}
