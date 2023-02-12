package flow

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        println("$it milliseconds") // almost 2 seconds because it is sequential.
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
        println("$it milliseconds") // almost 1 second because it is concurrent.
    }
}
