package util

import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.time.Duration

object TimerUtil {
    object OneTimeTimerUtil {
        private var timerTask: TimerTask? = null

        fun run(delay: Duration, action: TimerTask.() -> Unit) {
            timerTask?.cancel()
            timerTask = Timer().schedule(delay.inWholeMilliseconds, action)
        }

        fun cancel() {
            timerTask?.cancel()
        }
    }

    object PeriodicTimerUtil {
        private var timerTask: TimerTask? = null

        fun run(
            period: Duration,
            delay: Duration = Duration.ZERO,
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
