package util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

object FlowUtil {
    fun <T : Any> Flow<T>.onMisc() = onStart {
        println("👀onStart")
    }.onEach {
        println("👀onEach: $it")
    }.onEmpty {
        println("👀onEmpty") // completes without emitting any elements.
    }.onCompletion {
        println("👀 onCompletion: $it")
    }
}
