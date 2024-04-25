package util.datetime

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.minutes

object DeprecatedJavaUtilDate {
    fun create(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): Date =
            Calendar.getInstance().apply {
                set(year, month - 1, day, hour, minute, second)
            }.time

    fun create(localDateTime: LocalDateTime): Date = Date.from(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant())
    private fun minutesPastFromNow(minutes: Long = 0) = Date(System.currentTimeMillis() - minutes.minutes.inWholeMilliseconds)
    fun isWithinMinutesPastOrFuture(date: Date = Date(), minutes: Long = 0) = date.after(minutesPastFromNow(minutes))
}

private fun main() {
    println(DeprecatedJavaUtilDate.create(2025, 1, 2, 3, 4, 5))
    println(DeprecatedJavaUtilDate.create(LocalDateTime.now()))
}