package util

import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object TimerUtil {
    private var oneTimeTimerTask: TimerTask? = null
    private var periodicTimerTask: TimerTask? = null

    fun runOneTime(delay: Duration, action: TimerTask.() -> Unit) {
        oneTimeTimerTask?.cancel()
        oneTimeTimerTask = Timer().schedule(delay.inWholeMilliseconds, action)
    }

    fun runPeriodic(
        period: Duration,
        delay: Duration = Duration.ZERO,
        action: TimerTask.() -> Unit
    ) {
        periodicTimerTask?.cancel()
        periodicTimerTask = Timer().schedule(
            delay = delay.inWholeMilliseconds,
            period = period.inWholeMilliseconds,
            action = action
        )
    }
}

private fun main() {
    TimerUtil.runOneTime(delay = 3.seconds) { println("Hello") }
    TimerUtil.runPeriodic(period = 3.seconds) { println("World") }
}
