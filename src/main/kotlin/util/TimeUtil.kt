package util

import kotlin.time.Duration.Companion.milliseconds

object TimeUtil {
    val java.time.Duration.kotlinDuration: kotlin.time.Duration
        get() = toMillis().milliseconds
}
