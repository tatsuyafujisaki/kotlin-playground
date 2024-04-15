package util

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object YearMonthUtil {
    fun parseStringOrNull(text: String, pattern: String) = runCatching {
        YearMonth.parse(text, DateTimeFormatter.ofPattern(pattern))
    }.getOrNull()

    /**
     * @return only the current month if n = 0. both current month and last month if n = 1, and so on.
     */
    fun getPastMonths(n: Int = 0): List<YearMonth> {
        val now = YearMonth.now()
        return List(n + 1) {
            now.minusMonths(it.toLong())
        }
    }

    /**
     * @return the current month if n = 0. last month if n = 1, and so on.
     */
    fun getPastMonth(n: Int = 0): YearMonth = YearMonth.now().minusMonths(n.toLong())

    fun convertToDateAtDayOne(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atDay(1)
    fun convertToDateAtEndOfMonth(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atEndOfMonth()
}

private fun main() {
    println(YearMonthUtil.getPastMonths(0))
    println(YearMonthUtil.getPastMonth(0))
}
