package util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object FlowUtil {
    fun <T> buildFlow(interval: Long = 1000, emitter: () -> T) = flow {
        while (true) {
            emit(emitter())
            delay(interval)
        }
    }

    private fun createTimer(interval: Long = 1000) = generateSequence(0L, Long::inc).asFlow().onEach {
        delay(interval)
    }

    /**
     * Thanks to [shareIn], a [flow] builder is called only once even if there are several collectors.
     */
    private fun createSharedTimer(interval: Long = 1000) =
        createTimer(interval).shareIn(GlobalScope, SharingStarted.WhileSubscribed())

    fun flowExample() {
        runBlocking {
            launch {
                createTimer().collect {
                    println(it)
                }
            }
        }
    }

    fun sharedFlowExample() {
        runBlocking {
            val sharedTimer = createSharedTimer()

            sharedTimer.onEach {
                println(it)
            }.launchIn(GlobalScope)

            sharedTimer.onEach {
                println(it)
            }.launchIn(GlobalScope)

            delay(10_000)
        }
    }
}
