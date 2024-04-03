package util

import util.datetime.DateTimeUtil.DateUtil.japaneseDatePassed
import util.datetime.DateTimeUtil.formatJapanesePassedDateTime
import java.time.LocalDate
import java.time.LocalDateTime

private fun main() {
    val dateTime1 = LocalDateTime.of(2024, 2, 20, 23, 59, 0)
    val dateTime2 = LocalDateTime.of(2024, 3, 22, 0, 0, 0)
    println(formatJapanesePassedDateTime(dateTime1, dateTime2))

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
