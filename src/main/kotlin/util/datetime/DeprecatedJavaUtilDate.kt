package util.datetime

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date
import kotlin.time.Duration.Companion.minutes

object DeprecatedJavaUtilDate {
    fun create(localDateTime: LocalDateTime) = Date.from(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant())

    private fun minutesPastFromNow(minutes: Long = 0) = Date(System.currentTimeMillis() - minutes.minutes.inWholeMilliseconds)

    fun isWithinMinutesPastOrFuture(date: Date = Date(), minutes: Long = 0) = date.after(minutesPastFromNow(minutes))
}

private fun main() {
    println(DeprecatedJavaUtilDate.create(LocalDateTime.now()))
}