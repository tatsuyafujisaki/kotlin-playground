package util.datetime

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.minutes

object LegacyJavaDateUtil {
    fun create(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Date = Calendar.getInstance().apply {
        set(year, month - 1, day, hour, minute, second)
    }.time

    fun create(localDateTime: LocalDateTime): Date = Date.from(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant())
    fun isLessThanMinutesOld(pastDate: Date, minutes: Long) = pastDate.after(Date(System.currentTimeMillis() - minutes.minutes.inWholeMilliseconds))
}

private fun main() {
    println(LegacyJavaDateUtil.create(2025, 1, 2, 3, 4, 5))
}
