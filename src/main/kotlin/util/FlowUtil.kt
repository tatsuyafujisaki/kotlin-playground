package util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart

object FlowUtil {
    fun <T : Any> Flow<T>.onMisc() = onStart {
        println("doOnSubscribe")
    }.onEach {
        println("onEach: $it")
    }.onEmpty {
        println("onEmpty") // completes without emitting any elements.
    }.onCompletion {
        println("onCompletion" + if (it == null) "without a throwable" else "with throwable: $it")
    }

    fun <T> buildFlow(interval: Long = 1000, emitter: () -> T) = flow {
        while (true) {
            emit(emitter())
            delay(interval)
        }
    }
}
