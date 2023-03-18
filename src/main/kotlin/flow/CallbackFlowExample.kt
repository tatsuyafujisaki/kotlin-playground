package flow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

private class CountDownTimer(
    val total: Long,
    val interval: Long,
    val onFinish: () -> Unit
) {
    private var onTick: ((millisUntilFinished: Long) -> Unit)? = null

    val flow = callbackFlow {
        onTick = { trySend(it) }
        awaitClose {}
    }

    suspend fun start() {
        for (i in total downTo 0 step interval) {
            onTick?.invoke(i)
            delay(interval)
        }
        onFinish()
    }
}

private suspend fun main() {
    coroutineScope {
        val countDownTimer = CountDownTimer(total = 3_000, interval = 1_000, ::cancel)

        launch {
            countDownTimer
                .flow
                .onCompletion {
                    println("onCompletion")
                }.collect {
                    println("collect: $it")
                }
        }

        countDownTimer.start()
    }
}
