package util

object IntUtil {
    fun Int.between(fromInclusive: Int, toExclusive: Int) = this in fromInclusive until toExclusive

    fun Int.nth(): String {
        val lastDigit = this % 10
        return toString() + when {
            this in 11..13 || lastDigit !in 1..3 -> "th"
            lastDigit == 1 -> "st"
            lastDigit == 2 -> "nd"
            lastDigit == 3 -> "rd"
            else -> error("Impossible state")
        }
    }
}
