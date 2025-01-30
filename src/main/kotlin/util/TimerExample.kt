package util

import java.time.LocalTime
import java.util.Timer
import kotlin.concurrent.schedule

private fun main() {
    val timer = Timer()
    val timerTask1 = timer.schedule(
        delay = 1_000,
        period = 1_000
    ) {
        println("🍎" + LocalTime.now())
    }
    val timerTask2 = timer.schedule(
        delay = 1_000,
        period = 1_000
    ) {
        println("🍊" + LocalTime.now())
    }
    timerTask1.cancel() // only cancels timerTask1, while timerTask2 continues to work.
    timerTask2.cancel() // only cancels timerTask2, while timerTask1 continues to work.

    // cancels all the TimerTask(s).
    // After that, if you try to call `schedule()` on this canceled timer,
    // the "IllegalStateException: Timer already cancelled." error will be thrown.
    timer.cancel()

    println("Done!")
}