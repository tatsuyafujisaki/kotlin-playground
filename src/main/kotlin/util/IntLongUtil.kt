package util

import util.CollectionUtil.permute
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

object IntLongUtil {
    val Int.isEven get() = this % 2 == 0
    val Int.isOdd get() = this % 2 == 1
    val Int.isSquareNumber get() = sqrt(toDouble()) % 1.0 == 0.0

    /** O(sqrt(n)) */
    val Int.isPrime: Boolean
        get() {
            if (this == 2) return true
            if (this < 2 || this % 2 == 0) return false
            for (i in 3..sqrt(toDouble()).toInt() step 2) {
                if (this % i == 0) return false
            }
            return true
        }

    val Int.largestDivisor: Int
        get() {
            for (i in 2..this) {
                if (this % i == 0) return this / i
            }
            return 1
        }

    val Int.nth: String
        get() {
            val lastDigit = this % 10
            return toString() + when {
                this in 11..13 || lastDigit !in 1..3 -> "th"
                lastDigit == 1 -> "st"
                lastDigit == 2 -> "nd"
                lastDigit == 3 -> "rd"
                else -> error("Impossible state")
            }
        }

    fun Int.sqrtInt() = sqrt(toDouble()).toInt()
    fun Int.permute() = (1..this).toList().permute()
    fun Int.between(fromInclusive: Int, toExclusive: Int) = this in fromInclusive until toExclusive
    fun Int.pow(n: Int) = toDouble().pow(n).toInt() // returns Int.MAX_VALUE when it overflowed.
    fun Int.pow(n: Long) = toDouble().pow(n.toInt()).toLong()
    fun Long.pow(n: Int) = toDouble().pow(n).toLong()
    fun Long.pow(n: Long) = toDouble().pow(n.toInt()).toLong()
    fun Long.divideAndCeil(divisor: Long) = ceil(toDouble() / divisor).toLong()
    fun Long.divideAndFloor(divisor: Long) = floor(toDouble() / divisor).toLong()
    fun divMod(a: Int, b: Int) = a / b to a % b

    /**
     * Iterative method
     * @receiver must be greater than or equal to 0.
     */
    fun Int.factorial() = if (this <= 1) 1 else (2..this).reduce(Int::times)

    /**
     * Tail recursive method
     * @receiver must be greater than or equal to 0.
     */
    tailrec fun Int.factorialRecursive(acc: Int = 1): Int =
        if (this <= 1) acc else (this - 1).factorialRecursive(acc * this)

    fun nPr(n: Int, r: Int) = n.factorial().toDouble() / (n - r).factorial()
    fun nCr(n: Int, r: Int) = nPr(n, r) / r.factorial()

    /** @return 1, 2, ..., or 6, if sides is 6 */
    fun rollDice(sides: Int = 6) = (1..sides).random()
}
