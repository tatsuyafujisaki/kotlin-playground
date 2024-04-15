package util

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object YearMonthUtil {
    fun parseStringOrNull(text: String, pattern: String) = runCatching {
        YearMonth.parse(text, DateTimeFormatter.ofPattern(pattern))
    }.getOrNull()

    /**
     * @param n 0 means current month, 1 means last month, 2 means the month before last month, and so on.
     */
    fun getPastMonths(n: Int = 0): List<YearMonth> {
        val now = YearMonth.now()
        return List(n + 1) {
            now.minusMonths(it.toLong())
        }
    }

    fun convertToDateAtDayOne(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atDay(1)
    fun convertToDateAtEndOfMonth(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atEndOfMonth()
}

private fun main() {
    val pastMonths = YearMonthUtil.getPastMonths(0)
    println(pastMonths)
}
