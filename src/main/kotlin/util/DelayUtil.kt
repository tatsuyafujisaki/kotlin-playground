package util

import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

object DelayUtil {
    fun delay(milliseconds: Long, action: (TimerTask) -> Unit) {
        Timer().schedule(milliseconds, action)
    }
}
