package util

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object YearMonthUtil {
    fun parseStringOrNull(text: String, pattern: String) = runCatching {
        YearMonth.parse(text, DateTimeFormatter.ofPattern(pattern))
    }.getOrNull()

    fun convertToDateAtDayOne(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atDay(1)
    fun convertToDateAtEndOfMonth(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atEndOfMonth()
}
