package util

import kotlin.math.E
import kotlin.math.pow
import kotlin.math.sqrt
import util.IntegerUtil.factorial
import util.IntegerUtil.nCr
import util.IntegerUtil.pow

object MathUtil {
    fun Collection<Int>.weightedMean(weights: Collection<Int>) = zip(weights) { x, weight -> x * weight }.sum().toDouble() / weights.sum()

    /**
     * @receiver must be sorted.
     */
    fun List<Int>.median(): Double {
        val i = size / 2
        return if (size % 2 == 0) (this[i - 1] + this[i]) / 2.0 else this[i].toDouble()
    }

    fun Collection<Int>.mode() = groupingBy { it }.eachCount().maxByOrNull { it.value }?.key

    fun Collection<Int>.standardDeviation(): Double {
        val mean = average()
        return sqrt(sumByDouble { (it - mean).pow(2) } / size)
    }

    @JvmName("standardDeviationDouble")
    fun Collection<Double>.standardDeviation(): Double {
        val mean = average()
        return sqrt(sumByDouble { (it - mean).pow(2) } / size)
    }

    fun covariance(xs: Collection<Double>, ys: Collection<Double>): Double {
        val meanX = xs.average()
        val meanY = ys.average()
        return xs.zip(ys) { x, y -> (x - meanX) * (y - meanY) }.sum() / xs.count()
    }

    // Pearson correlation coefficient
    fun pearson(xs: List<Double>, ys: List<Double>) = covariance(xs, ys) / (xs.standardDeviation() * ys.standardDeviation())

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
     * @param p probability of success
     * @param k trials
     * @return probability of the first success on the [k]th trial.
     */
    fun geometricProbability(p: Double, k: Int) = (1 - p).pow(k - 1) * p

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
        return 1.0 - (6.0 * rank(xs).zip(rank(ys)) { rankX, rankY -> (rankX - rankY).pow(2) }.sum() / (n * (n * n - 1.0)))
    }

    fun fibonacci() =
        generateSequence(0 to 1) { it.second to it.first + it.second }.map { it.first }
}
