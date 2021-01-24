package util

import kotlin.math.pow

object IntLongUtil {
    fun Int.between(fromInclusive: Int, toExclusive: Int) = this in fromInclusive until toExclusive

    /** If overflowed, returns Int.MAX_VALUE. */
    fun Int.pow(n: Int) = toDouble().pow(n).toInt()
    fun Int.powToLong(n: Int) = toDouble().pow(n).toLong()
    fun Long.pow(n: Int) = toDouble().pow(n).toLong()

    fun Int.isEven() = this % 2 == 0
    fun Int.isSquareNumber() = (kotlin.math.sqrt(toDouble())) % 1.0 == 0.0
    fun Int.sqrt() = kotlin.math.sqrt(toDouble()).toInt()

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

    /**
     * Iterative method
     * @receiver must be greater than or equal to 0.
     */
    fun Int.factorial() = if (this <= 1) 1 else (2..this).reduce(Int::times)

    /**
     * Tail recursive method
     * @receiver must be greater than or equal to 0.
     */
    tailrec fun Int.factorialRecursive(acc: Int = 1): Int = if (this <= 1) acc else (this - 1).factorialRecursive(acc * this)
}
