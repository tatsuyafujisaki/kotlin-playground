package util

import kotlin.math.pow
import kotlin.math.sqrt

object IntegerUtil {
    fun Int.between(fromInclusive: Int, toExclusive: Int) = this in fromInclusive until toExclusive

    /** If overflowed, returns Int.MAX_VALUE. */
    fun Int.pow(n: Int) = toDouble().pow(n).toInt()
    fun Int.powToLong(n: Int) = toDouble().pow(n).toLong()
    fun Long.pow(n: Int) = toDouble().pow(n).toLong()

    fun Int.isEven() = this % 2 == 0
    fun Int.isSquareNumber() = sqrt(toDouble()) % 1.0 == 0.0
    fun Int.sqrtInt() = sqrt(toDouble()).toInt()

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

    // O(sqrt(n))
    fun Int.isPrime(): Boolean {
        if (this == 2) return true
        if (this < 2 || this % 2 == 0) return false
        for (i in 3..sqrt(toDouble()).toInt() step 2) {
            if (this % i == 0) return false
        }
        return true
    }

    fun Int.largestDivisor(): Int {
        for (i in 2..this) {
            if (this % i == 0) return this / i
        }
        return 1
    }

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
