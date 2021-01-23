package util

import kotlin.math.E
import kotlin.math.pow
import kotlin.math.sqrt

object MathUtil {
    fun Int.pow(n: Int): Int {
        val powered = toDouble().pow(n).toInt()
        require(powered < Int.MAX_VALUE) {
            "Overflowed. Instead, use Int.powToLong()."
        }
        return powered
    }

    fun Long.pow(n: Int) = toDouble().pow(n).toLong()
    fun Int.powToLong(n: Int) = toDouble().pow(n).toLong()

    fun Int.isEven() = this % 2 == 0
    fun Int.isSquareNumber() = (sqrt(toDouble())) % 1.0 == 0.0
    fun Int.sqrt() = sqrt(toDouble()).toInt()

    fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun largestDivisor(n: Int): Int {
        for (i in 2..n) {
            if (n % i == 0) {
                return n / i
            }
        }
        return 1
    }

    fun round(x: Double, decimals: Int) = "%.${decimals}f".format(x)

    fun weightedMean(xs: List<Int>, weights: List<Int>) =
        (xs.zip(weights) { x, weight -> x * weight }).sum() / weights.sum().toDouble()

    /**
     * @param xs must be sorted.
     */
    fun median(xs: List<Int>): Double {
        val i = xs.size / 2
        return when (xs.size % 2) {
            0 -> (xs[i - 1] + xs[i]) / 2.0
            else -> xs[i].toDouble()
        }
    }

    /**
     * @param xs must be sorted if you want to return the smallest mode.
     */
    fun mode(xs: List<Int>) = xs.groupingBy { it }.eachCount().maxByOrNull { it.value }!!.key

    fun standardDeviation(xs: List<Double>): Double {
        val mean = xs.average()
        return sqrt(xs.map { (it - mean).pow(2) }.sum() / xs.size)
    }

    fun cov(xs: List<Double>, ys: List<Double>): Double {
        val meanX = xs.average()
        val meanY = ys.average()
        return xs.zip(ys) { x, y -> (x - meanX) * (y - meanY) }.sum() / xs.count()
    }

    // Pearson correlation coefficient
    fun pearson(xs: List<Double>, ys: List<Double>) = cov(xs, ys) / (standardDeviation(xs) * standardDeviation(ys))

    /**
     * Iterative method
     * @param n must be greater than or equal to 0.
     */
    fun factorial(n: Int) = if (n <= 1) 1 else (2..n).reduce(Int::times)

    /**
     * Tail recursive method
     * @param n must be greater than or equal to 0.
     */
    tailrec fun factorial(n: Int, acc: Int = 1): Int = if (n <= 1) acc else factorial(n - 1, acc * n)

    fun nPr(n: Int, r: Int) = factorial(n) / factorial(n - r).toDouble()
    fun nCr(n: Int, r: Int) = factorial(n) / (factorial(r) * factorial(n - r).toDouble())

    fun cumulativeBinomialProbability(n: Int, rs: IntRange, p: Double) =
        rs.sumByDouble { nCr(n, it) * p.pow(it) * (1 - p).pow(n - it) }

    /**
     * @param lambda average number of successes
     * @param k actual number of successes
     */
    fun poissonProbability(lambda: Double, k: Int) = lambda.pow(k) * E.pow(-lambda) / factorial(k)

    /**
     * @param xs must be sorted.
     */
    fun firstQuartile(xs: List<Int>) = median(xs.subList(0, (xs.size / 2.0).toInt()))

    /**
     * @param xs must be sorted.
     */
    fun thirdQuartile(xs: List<Int>): Double {
        val n = xs.size
        return median(xs.subList(n - (n / 2.0).toInt(), n))
    }

    /**
     * @param xs must be sorted.
     */
    fun interquartileRange(xs: List<Int>) = thirdQuartile(xs) - firstQuartile(xs)

    fun rank(xs: List<Double>): List<Int> {
        val sorted = xs.sorted()
        return xs.map { sorted.indexOf(it) + 1 }
    }

    // Spearman's rank correlation coefficient
    fun spearman(xs: List<Double>, ys: List<Double>): Double {
        val n = xs.size
        return 1.0 - (6.0 * rank(xs).zip(rank(ys)) { rankX, rankY -> (rankX - rankY).pow(2) }
            .sum() / (n * (n * n - 1.0)))
    }

    // O(sqrt(n))
    fun isPrime(n: Int): Boolean {
        if (n == 2) {
            return true
        }
        if (n < 2 || n % 2 == 0) {
            return false
        }
        for (i in 3..n.sqrt() step 2) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }

    fun fibonacci() =
        generateSequence(0 to 1) { it.second to it.first + it.second }.map { it.first }
}
