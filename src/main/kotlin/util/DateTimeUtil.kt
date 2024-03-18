package util

import util.DateTimeUtil.DateUtil.japaneseDatePassed
import util.DateTimeUtil.japaneseDateTimePassed
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.time.toKotlinDuration
import java.util.Date

object DateTimeUtil {
    fun convertJavaUtilDateToLocalDateTime(date: Date): LocalDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

    fun japaneseDateTimePassed(dateTime1: LocalDateTime, date2: LocalDateTime): String {
        val duration = Duration.between(dateTime1, date2).toKotlinDuration()
        return when {
            duration.inWholeDays > 0 -> "${duration.inWholeDays}日前"
            duration.inWholeHours > 0 -> "${duration.inWholeHours}時間前"
            duration.inWholeMinutes > 0 -> "${duration.inWholeMinutes}分前"
            duration.inWholeSeconds > 0 -> "${duration.inWholeSeconds}秒前"
            else -> ""
        }
    }

    object DateUtil {
        /**
         * @param date yyyy-mm-dd
         */
        fun convertToDate(date: String) = runCatching {
            LocalDate.parse(date)
        }.getOrNull()

        fun formatToJapaneseDate(date: LocalDate = LocalDate.now()): String =
                date.format(DateTimeFormatter.ofPattern("y年M月d日"))

        fun japaneseDatePassed(date1: LocalDate, date2: LocalDate): String {
            val diffInDays = ChronoUnit.DAYS.between(date1, date2)
            return if (diffInDays > 0) "${diffInDays}日前" else "本日"
        }
    }

    object TimeUtil {
        private fun convertToHoursMinutesSeconds(totalSeconds: Long): Triple<Long, Long, Long> {
            val hours = totalSeconds / 3600
            val minutes = (totalSeconds % 3600) / 60
            val seconds = totalSeconds % 60
            return Triple(hours, minutes, seconds)
        }

        fun hhMmSs(totalSeconds: Long): String {
            val (hours, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
            return "%02d:%02d:%02d".format(hours, minutes, seconds)
        }

        fun mmSs(totalSeconds: Long): String {
            val (_, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
            return "%02d:%02d".format(minutes, seconds)
        }

        fun hhMmSsOrMmSs(totalSeconds: Long): String {
            val (hours, minutes, seconds) = convertToHoursMinutesSeconds(totalSeconds)
            return if (hours > 0) {
                "%02d:%02d:%02d".format(hours, minutes, seconds)
            } else {
                "%02d:%02d".format(minutes, seconds)
            }
        }
    }
}

private fun main() {
    val dateTime1 = LocalDateTime.of(2024, 2, 20, 23, 59, 0)
    val dateTime2 = LocalDateTime.of(2024, 3, 22, 0, 0, 0)
    println(japaneseDateTimePassed(dateTime1, dateTime2))

    val date1 = LocalDate.of(2024, 2, 1)
    val date2 = LocalDate.of(2024, 2, 20)
    println(japaneseDatePassed(date1, date2))
}

//private fun main() {
//    val date = convertToDate("1200-12-10")!!
//    println(convertToJapaneseDate(date))
//}

//private fun main() {
//    val duration = 1.hours + 2.minutes + 3.seconds
//
//    println(DateTimeUtil.TimeUtil.hhMmSs(duration.inWholeSeconds))
//    println(DateTimeUtil.TimeUtil.mmSs(duration.inWholeSeconds))
//    println(DateTimeUtil.TimeUtil.hhMmSsOrMmSs(duration.inWholeSeconds))
//}
