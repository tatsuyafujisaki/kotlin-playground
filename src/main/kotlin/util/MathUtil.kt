package util

import factorial
import kotlin.math.E
import kotlin.math.pow
import kotlin.math.sqrt

object MathUtil {
    fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun IntArray.swap(i: Int, j: Int) {
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

    fun Collection<Int>.weightedMean(weights: Collection<Int>) = zip(weights) { x, weight -> x * weight }.sum().toDouble() / weights.sum()

    /**
     * @receiver must be sorted.
     */
    fun List<Int>.median(): Double {
        val i = size / 2
        return if (size % 2 == 0) (this[i - 1] + this[i]) / 2.0 else this[i].toDouble()
    }

    fun Collection<Int>.mode() = groupingBy { it }.eachCount().maxByOrNull { it.value }?.key

    fun List<Int>.standardDeviation(): Double {
        val mean = average()
        return sqrt(map { (it - mean).pow(2) }.sum() / size)
    }

    @JvmName("standardDeviationDouble")
    fun List<Double>.standardDeviation(): Double {
        val mean = average()
        return sqrt(map { (it - mean).pow(2) }.sum() / size)
    }

    fun cov(xs: List<Double>, ys: List<Double>): Double {
        val meanX = xs.average()
        val meanY = ys.average()
        return xs.zip(ys) { x, y -> (x - meanX) * (y - meanY) }.sum() / xs.count()
    }

    // Pearson correlation coefficient
    fun pearson(xs: List<Double>, ys: List<Double>) = cov(xs, ys) / (xs.standardDeviation() * ys.standardDeviation())

    fun nPr(n: Int, r: Int) = n.factorial().toDouble() / (n - r).factorial()
    fun nCr(n: Int, r: Int) = n.factorial().toDouble() / (r.factorial() * (n - r).factorial())

    /**
     * @param n number of children
     * @param r number of boys
     * @param p probability of having a boy
     * @return probability that families with [n] children have [r] boys.
     * i.e. probability of having [r] boys + probability of having ([r] + 1) boys + ... + probability of having [n] boys
     */
    fun binomialProbability(n: Int, r: Int, p: Double) = nCr(n, r) * p.pow(r) * (1 - p).pow(n - r)

    /**
     * Redundant wrapper
     * @param n number of children
     * @param rs range of numbers of boys
     * @param p probability of having a boy
     * @return probability that families with [n] children have [rs] boys.
     * e.g. If [n] is 6 and [rs] is [4, 5, 6], the returned value is the probability of having 4 boys + probability of having 5 boys + ... + probability of having 6 boys.
     * i.e. probability of having at least 6 boys
     */
    fun cumulativeBinomialProbability(n: Int, rs: IntRange, p: Double) = rs.sumByDouble { binomialProbability(n, it, p) }

    /**
     * @param lambda average number of successes
     * @param k actual number of successes
     */
    fun poissonProbability(lambda: Double, k: Int) = lambda.pow(k) * E.pow(-lambda) / k.factorial()

    /**
     * If the number of elements is odd, the median element is excluded.
     * @receiver must be sorted.
     */
    fun Collection<Int>.firstQuartile() = take(size / 2).median()

    /**
     * If the number of elements is odd, the median element is excluded.
     * @receiver must be sorted.
     */
    fun List<Int>.thirdQuartile() = takeLast(size / 2).median()

    /**
     * @receiver must be sorted.
     */
    fun List<Int>.interquartileRange() = thirdQuartile() - firstQuartile()

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
