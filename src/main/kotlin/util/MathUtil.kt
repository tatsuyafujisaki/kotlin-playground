package util

import kotlin.math.E
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt
import util.IntLongUtil.factorial
import util.IntLongUtil.nCr

object MathUtil {
    fun fibonacci() = generateSequence(0 to 1) { it.second to it.first + it.second }.map { it.first }
    fun Collection<Int>.mode() = groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
    fun Collection<Int>.weightedMean(weights: Collection<Int>) = zip(weights) { x, weight -> x * weight }.sum().toDouble() / weights.sum()

    /**
     * @receiver must be sorted.
     */
    fun List<Int>.median(): Double {
        val i = size / 2
        return if (size % 2 == 0) (this[i - 1] + this[i]) / 2.0 else this[i].toDouble()
    }

    val Collection<Int>.standardDeviation: Double
        get() {
            val mean = average()
            return sqrt(sumByDouble { (it - mean).pow(2) } / size)
        }

    @JvmName("standardDeviationDouble")
    fun Collection<Double>.standardDeviation(): Double {
        val mean = average()
        return sqrt(sumByDouble { (it - mean).pow(2) } / size)
    }

    fun covariance(xs: Collection<Int>, ys: Collection<Int>): Double {
        val meanX = xs.average()
        val meanY = ys.average()
        return xs.zip(ys) { x, y -> (x - meanX) * (y - meanY) }.sum() / xs.size
    }

    @JvmName("covarianceDouble")
    fun covariance(xs: Collection<Double>, ys: Collection<Double>): Double {
        val meanX = xs.average()
        val meanY = ys.average()
        return xs.zip(ys) { x, y -> (x - meanX) * (y - meanY) }.sum() / xs.size
    }

    fun correlationCoefficient(xs: Collection<Int>, ys: Collection<Int>) = covariance(xs, ys) / (xs.standardDeviation * ys.standardDeviation)

    @JvmName("correlationCoefficientDouble")
    fun correlationCoefficient(xs: Collection<Double>, ys: Collection<Double>) = covariance(xs, ys) / (xs.standardDeviation() * ys.standardDeviation())

    /**
     * @param p probability of success
     * @param n number of trials
     * @param k number of successes
     * @return probability of [k] successes on [n] trials
     */
    fun binomialProbability(p: Double, n: Int, k: Int) = nCr(n, k) * p.pow(k) * (1 - p).pow(n - k)

    /**
     * @param p probability of success
     * @param n number of trials
     * @param ks number of successes
     * @return probability of at least [ks] successes on [n] trials
     * e.g. If [n] is 6 and [ks] is [4, 5, 6], the returned value is the probability of 4 successes + probability of 5 successes + probability of 6 successes.
     * i.e. probability of at least 6 successes
     */
    fun cumulativeBinomialProbability(p: Double, n: Int, ks: IntRange) = ks.sumByDouble { binomialProbability(p, n, it) }

    /**
     * Redundant wrapper for geometric probability
     * @param p probability of success
     * @param k number of trials
     * @return probability of the first success on the [k]th trial.
     */
    fun firstSuccessOnKthTrialProbability(p: Double, k: Int) = (1 - p).pow(k - 1) * p

    /**
     * Redundant wrapper for geometric probability
     * @param p probability of success
     * @param k number of trials
     * @return probability that all the trials fail
     */
    fun allTrialsFailProbability(p: Double, k: Int) = (1 - p).pow(k)

    /**
     * @param mean average number of successes
     * @param k actual number of successes
     */
    fun poissonProbability(mean: Double, k: Int) = mean.pow(k) * E.pow(-mean) / k.factorial()

    /**
     * E\[X] in the Poisson distribution = [mean] (average number of successes)
     * Var(X) in the Poisson distribution = [mean] (actual number of successes)
     *
     * Var(X) = E[X^2] - E\[X]^2
     * -> E[X^2] = Var(X) + E\[X]^2 = [mean] + [mean]^2
     */
    fun squaredPoissonProbability(mean: Double) = mean + mean.pow(2)

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

    /**
     * Spearman's rank correlation coefficient
     */
    fun spearman(xs: List<Double>, ys: List<Double>): Double {
        fun List<Double>.rank(): List<Int> {
            val sorted = sorted()
            return map { sorted.indexOf(it) + 1 }
        }

        val n = xs.size
        return 1 - (6 * xs.rank().zip(ys.rank()) { rankX, rankY -> (rankX - rankY).toDouble().pow(2) }.sum() / (n * (n * n - 1)))
    }

    fun sigmoid(x: Double) = 1 / (1 + exp(-x))
}
