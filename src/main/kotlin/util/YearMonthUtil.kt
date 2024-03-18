package util

import java.time.LocalDate
import java.time.YearMonth

object YearMonthUtil {
    fun convertToDateAtDayOne(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atDay(1)
    fun convertToDateAtEndOfMonth(yearMonth: YearMonth = YearMonth.now()): LocalDate = yearMonth.atEndOfMonth()
}
