package flow

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

private interface MyCallback {
    fun onTick(millisUntilFinished: Long)
    fun onFinish()
}

private class CountDownTimer(val total: Long, val interval: Long = 1_000) {
    var myCallback: MyCallback? = null

    val flow = callbackFlow {
        myCallback = object : MyCallback {
            override fun onTick(millisUntilFinished: Long) {
                trySend(millisUntilFinished)
            }

            override fun onFinish() {
                println("onFinish")
            }
        }
        awaitClose {}
    }

    suspend fun start() {
        for (i in total downTo 0 step interval) {
            myCallback?.onTick(i)
            delay(interval)
        }
        myCallback?.onFinish()
    }
}

private suspend fun main() = coroutineScope {
    val countDownTimer = CountDownTimer(3_000)

    launch {
        countDownTimer.flow.collect {
            println("collect: $it")
        }
    }

    countDownTimer.start()
}
