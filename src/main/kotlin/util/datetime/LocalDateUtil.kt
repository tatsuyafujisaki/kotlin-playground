package util.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

object LocalDateUtil {
    fun create(year: Int, month: Int, day: Int) = LocalDate.of(year, month, day)
    fun create(date: Date) = LocalDate.ofInstant(Instant.ofEpochMilli(date.time), ZoneId.systemDefault())

    /**
     * @param date yyyy-mm-dd
     */
    fun createOrNull(date: String) = runCatching { LocalDate.parse(date) }.getOrNull()
    fun isLessThanDaysOld(pastDate: LocalDate = LocalDate.now(), days: Long) = pastDate.isAfter(LocalDate.now().minusDays(days))
    fun formatToJapaneseDate(date: LocalDate = LocalDate.now()): String = date.format(DateTimeFormatter.ofPattern("y年M月d日"))
    fun japaneseDatePassed(date1: LocalDate, date2: LocalDate): String {
        val diffInDays = ChronoUnit.DAYS.between(date1, date2)
        return if (diffInDays > 0) "${diffInDays}日前" else "本日"
    }

    private fun getMonth(date: LocalDate = LocalDate.now(), monthDelta: Long = 0) = if (monthDelta >= 0) {
        date.plusMonths(monthDelta)
    } else {
        date.minusMonths(-1 * monthDelta)
    }.monthValue

    fun getMonthExample() {
        println("Next month: ${getMonth(monthDelta = -1)}")
        println("Current month: ${getMonth()}")
        println("Previous month: ${getMonth(monthDelta = 1)}")
    }
}

private fun main() {
    println(LocalDateTimeUtil.create(2025, 1, 2, 3, 4, 5))
    println(LocalDateTimeUtil.create(Date()))
}
