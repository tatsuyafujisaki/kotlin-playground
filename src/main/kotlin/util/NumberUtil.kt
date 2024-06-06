package util

import util.CollectionUtil.permute
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

object NumberUtil {
    fun bits(x: Int) = Integer.toBinaryString(x).padStart(Int.SIZE_BITS, '0')

    /** @receiver must be 0 or positive. */
    fun bitsForNonNegativeInt(x: Int) = x.toString(2).padStart(Int.SIZE_BITS, '0')

    fun ithBit(x: Int, i: Int) = (x shr i) and 1

    fun isEven(x: Int) = x % 2 == 0
    fun isOdd(x: Int) = x % 2 == 1
    private fun sqrtInt(x: Int) = sqrt(x.toDouble()).toInt()

    fun isPerfectSquare(x: Int) = sqrtInt(x).let {
        x == it * it
    }

    /** O(sqrt(n)) */
    fun isPrime(x: Int) = when {
        x == 2 -> true
        x < 2 || x % 2 == 0 -> false
        else -> (3..sqrt(x.toDouble()).toInt() step 2).none { x % it == 0 }
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
     * The power of n
     * @param n the exponent
     * @return 2 ^ [n]
     */
    fun pow2(n: Int) = 1 shl n

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

    fun nPr(n: Int, r: Int) = n.factorial().toDouble() / (n - r).factorial()
    fun nCr(n: Int, r: Int) = nPr(n, r) / r.factorial()

    /** @return 1, 2, ..., or 6, if sides is 6 */
    fun rollDice(sides: Int = 6) = (1..sides).random()
}
