package flow

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val countDownTimer = CountDownTimer(3_000)

    launch {
        flowFrom(countDownTimer).collect {
            println("collect: $it")
        }
    }

    countDownTimer.start()
}

private fun flowFrom(countDownTimer: CountDownTimer) = callbackFlow {
    countDownTimer.myCallback = object : MyCallback {
        override fun onTick(millisUntilFinished: Long) {
            trySend(millisUntilFinished)
        }

        override fun onFinish() {
            println("onFinish")
        }
    }
    awaitClose {}
}

private interface MyCallback {
    fun onTick(millisUntilFinished: Long)
    fun onFinish()
}

private class CountDownTimer(val millisInFuture: Long, val countDownInterval: Long = 1_000) {
    var myCallback: MyCallback? = null

    suspend fun start() {
        for (i in millisInFuture downTo 0 step countDownInterval) {
            myCallback?.onTick(i)
            delay(countDownInterval)
        }
        myCallback?.onFinish()
    }
}
