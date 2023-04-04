package util

import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

object TimeUtil {
    fun convertToHoursMinutesSeconds(totalSeconds: Long): Triple<Long, Long, Long> {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return Triple(hours, minutes, seconds)
    }
}

private fun main() {
    val duration = 12.hours + 34.minutes + 56.seconds
    val (hours, minutes, seconds) = TimeUtil.convertToHoursMinutesSeconds(duration.inWholeSeconds)

    println(hours)
    println(minutes)
    println(seconds)
}
