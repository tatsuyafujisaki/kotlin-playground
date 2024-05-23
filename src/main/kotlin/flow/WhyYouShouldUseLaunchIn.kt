package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

private suspend fun main() {
    measureTimeMillis {
        coroutineScope {
            launch {
                flowOf(Unit)
                        .onEach { delay(1_000) }
                        .collect()
                flowOf(Unit)
                        .onEach { delay(1_000) }
                        .collect()
            }
        }
    }.let {
        println("$it milliseconds") // prints almost 2 seconds because the two flows run in sequence.
    }

    measureTimeMillis {
        coroutineScope {
            flowOf(Unit)
                    .onEach { delay(1_000) }
                    .launchIn(this)
            flowOf(Unit)
                    .onEach { delay(1_000) }
                    .launchIn(this)
        }
    }.let {
        println("$it milliseconds") // prints almost 1 second because the two flows run in parallel.
    }
}
