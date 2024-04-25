package util.datetime

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date
import kotlin.time.toKotlinDuration

object LocalDateTimeUtil {
    fun create(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): LocalDateTime = LocalDateTime.of(year, month, day, hour, minute, second)
    fun create(epochSecond: Long) = LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC)
    fun create(date: Date): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.time), ZoneId.systemDefault())
    fun convertToEpochSecond(localDateTime: LocalDateTime) = localDateTime.toEpochSecond(ZoneOffset.UTC)
    fun isLessThanHoursOld(pastDateTime: LocalDateTime = LocalDateTime.now(), hours: Long) = pastDateTime.isAfter(LocalDateTime.now().minusHours(hours))
    fun convertObsoleteJavaUtilDateToLocalDateTime(date: Date = Date()): LocalDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    fun formatJapanesePassedDateTime(dateTime1: LocalDateTime, date2: LocalDateTime): String {
        val duration = Duration.between(dateTime1, date2).toKotlinDuration()
        return when {
            duration.inWholeDays > 0 -> "${duration.inWholeDays}日前"
            duration.inWholeHours > 0 -> "${duration.inWholeHours}時間前"
            duration.inWholeMinutes > 0 -> "${duration.inWholeMinutes}分前"
            duration.inWholeSeconds > 0 -> "${duration.inWholeSeconds}秒前"
            else -> ""
        }
    }
}

private fun main() {
    println(LocalDateTimeUtil.create(2025, 1, 2, 3, 4, 5))
    println(LocalDateTimeUtil.create(LocalDateTimeUtil.convertToEpochSecond(LocalDateTime.now())))
    println(LocalDateTimeUtil.create(Date()))
}
