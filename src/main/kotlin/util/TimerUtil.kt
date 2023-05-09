package util

import java.time.LocalTime
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import util.TimerUtil.PeriodicTimerUtil

object TimerUtil {
    class OneTimeTimerUtil {
        private var timerTask: TimerTask? = null

        fun run(delay: Duration, action: TimerTask.() -> Unit) {
            timerTask?.cancel()
            timerTask = Timer().schedule(delay.inWholeMilliseconds, action)
        }

        fun cancel() {
            timerTask?.cancel()
        }
    }

    class PeriodicTimerUtil {
        private var timerTask: TimerTask? = null

        fun run(
            period: Duration,
            delay: Duration = period,
            action: TimerTask.() -> Unit
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
    val timerUtil = PeriodicTimerUtil()
    timerUtil.run(period = 3.seconds) { println(LocalTime.now()) }
}
