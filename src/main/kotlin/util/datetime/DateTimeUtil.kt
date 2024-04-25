package util.datetime

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import kotlin.time.toKotlinDuration

object DateTimeUtil {
    fun isWithinHoursPastOrFuture(pastDateTime: LocalDateTime = LocalDateTime.now(), hours: Long = 0): Boolean =
            pastDateTime.isAfter(LocalDateTime.now().minusHours(hours))

    fun convertObsoleteJavaUtilDateToLocalDateTime(date: Date = Date()): LocalDateTime =
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

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
