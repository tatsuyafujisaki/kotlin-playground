package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

private suspend fun main(): Unit = coroutineScope {
    val mutableFlow = MutableSharedFlow<String>()
    val flow: Flow<String> = mutableFlow

    launch {
        flow.catch {
            println("Catch: $it")
        }.collect {
            println("Collect: $it")
        }
    }

    delay(1000)
    mutableFlow.emit("a")
    mutableFlow.emit("b")
    mutableFlow.emit("c")
}
