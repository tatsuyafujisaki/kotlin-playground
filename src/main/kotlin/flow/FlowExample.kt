package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

private suspend fun main(): Unit = coroutineScope {
    flow {
        emit("a")
        emit("b")
        emit("c")
    }.catch {
        println("catch: $it")
    }.collect {
        println(it)
    }
}
