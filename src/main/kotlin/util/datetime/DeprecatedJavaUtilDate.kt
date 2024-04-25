package util.datetime

import java.util.Date
import kotlin.time.Duration.Companion.minutes

object DeprecatedJavaUtilDate {
    private fun minutesPastFromNow(minutes: Long = 0) =
            Date(System.currentTimeMillis() - minutes.minutes.inWholeMilliseconds)

    fun isWithinMinutesPastOrFuture(date: Date = Date(), minutes: Long = 0) =
            date.after(minutesPastFromNow(minutes))
}

private fun main() {
    val date = Date(2024, 4, 25, 19, 30)
    println(DeprecatedJavaUtilDate.isWithinMinutesPastOrFuture(date))
}