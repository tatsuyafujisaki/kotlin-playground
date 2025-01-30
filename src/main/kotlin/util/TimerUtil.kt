package util

import util.TimerUtil.PeriodicTimer
import java.time.LocalTime
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object TimerUtil {
    class OneTimeTimer {
        private var timerTask: TimerTask? = null

        fun schedule(delay: Duration, action: TimerTask.() -> Unit) {
            timerTask?.cancel()
            timerTask = Timer().schedule(delay.inWholeMilliseconds, action)
        }

        fun cancel() {
            timerTask?.cancel()
        }
    }

    class PeriodicTimer {
        private var timerTask: TimerTask? = null

        fun schedule(
                period: Duration,
                delay: Duration = period,
                action: TimerTask.() -> Unit,
        ) {
            timerTask?.cancel()
            timerTask = Timer().schedule(
                    delay = delay.inWholeMilliseconds,
                    period = period.inWholeMilliseconds,
                    action = action
            )
        }

        fun cancel() {
            timerTask?.cancel()
        }
    }
}

private fun main() {
    val timer = PeriodicTimer()
    timer.schedule(period = 3.seconds) { println(LocalTime.now()) }
}
