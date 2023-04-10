package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import util.DateTimeUtil.DateUtil.convertToDate
import util.DateTimeUtil.DateUtil.convertToJapaneseDate

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
}

private fun main() {
    val date = convertToDate("1200-12-10")!!
    println(convertToJapaneseDate(date))
}

//private fun main() {
//    val duration = 1.hours + 2.minutes + 3.seconds
//
//    println(DateTimeUtil.TimeUtil.hhMmSs(duration.inWholeSeconds))
//    println(DateTimeUtil.TimeUtil.mmSs(duration.inWholeSeconds))
//    println(DateTimeUtil.TimeUtil.hhMmSsOrMmSs(duration.inWholeSeconds))
//}
