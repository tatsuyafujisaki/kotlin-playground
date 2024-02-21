package util

import java.time.format.DateTimeFormatter
import java.time.LocalDate
import java.time.LocalDateTime
import util.DateTimeUtil.DateUtil.convertToDate
import util.DateTimeUtil.DateUtil.convertToJapaneseDate
import util.DateTimeUtil.japaneseDiff
import java.time.Duration
import kotlin.time.toKotlinDuration

object DateTimeUtil {
    object DateUtil {
        /**
         * @param date yyyy-mm-dd
         */
        fun convertToDate(date: String) = runCatching {
            LocalDate.parse(date)
        }.getOrNull()

        fun convertToJapaneseDate(date: LocalDate): String = date.format(
            DateTimeFormatter.ofPattern("y年M月d日")
        )
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

    fun japaneseDiff(date1: LocalDateTime, date2: LocalDateTime): String {
        val diff = Duration.between(date1, date2).toKotlinDuration()
        return when {
            diff.inWholeDays > 0 -> "${diff.inWholeDays}日前"
            diff.inWholeHours > 0 -> "${diff.inWholeHours}時間前"
            diff.inWholeMinutes > 0 -> "${diff.inWholeMinutes}分前"
            diff.inWholeSeconds > 0 -> "${diff.inWholeSeconds}秒前"
            else -> ""
        }
    }
}

private fun main() {
    val dateTime1 = LocalDateTime.of(2024, 2, 20, 23, 59, 0);
    val dateTime2 = LocalDateTime.of(2024, 3, 22, 0, 0, 0);
    println(japaneseDiff(dateTime1, dateTime2))
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
