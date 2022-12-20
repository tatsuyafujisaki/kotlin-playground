package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

private suspend fun mutableSharedFlowExample(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>()
    val sharedFlow: Flow<String> = mutableSharedFlow

    launch {
        sharedFlow.collect {
            println("Collect: $it")
        }
    }

    // Purposefully get delayed to ensure that a collector starts
    // because the collector cannot collect a value emitted before the collector starts.
    delay(1000)
    mutableSharedFlow.emit("a")
    println("a is emitted.")
    mutableSharedFlow.emit("a")
    println("a is emitted. MutableSharedFlow can collect the same value.")
}

private suspend fun main(): Unit = coroutineScope {
    mutableSharedFlowExample()
}