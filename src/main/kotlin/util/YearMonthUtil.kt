package util

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object YearMonthUtil {
    fun parseOrNull(text: String, pattern: String) = runCatching {
        YearMonth.parse(text, DateTimeFormatter.ofPattern(pattern))
    }.getOrNull()

    /**
     * @return an empty list if n = 0. Last month if n = 1. Last two months if n = 2.
     */
    fun getPastMonths(n: Int = 0): List<YearMonth> {
        val now = YearMonth.now()
        return List(n) {
            now.minusMonths(it.toLong())
        }
    }

    /**
     * @return the current month if n = 0. Last month if n = 1. Second last month if n = 2.
     */
    fun getPastMonth(n: Int = 0): YearMonth = YearMonth.now().minusMonths(n.toLong())

    fun convertToDateAtDayOne(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atDay(1)
    fun convertToDateAtEndOfMonth(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atEndOfMonth()
}

private fun main() {
    println(YearMonthUtil.getPastMonths(6))
    println(YearMonthUtil.getPastMonth(6))
}
