package util

import util.NumberUtil.pow
import kotlin.math.pow

object DecibinaryUtil {
    /**
     * @param d the upper limit of the number of digits of a decibinary
     * @param s decimal
     * @return the number of decibinaries, which consist of [d] or less digits, and evaluate to [s]
     */
    fun getDecibinaryCount(d: Long, s: Long): Long = when {
        d == 0L && s == 0L -> 1
        d == 0L && s != 0L -> 0
        else -> LongRange(0, 9).map { // .map(...).sum() cannot be replaced by sumBy(...) because the latter does support Long.
            getDecibinaryCount(d - 1L, s - it * 2L.pow(d.toInt() - 1))
        }.sum()
    }

    fun toDecimal(deciBinary: Long): Long {
        var result = 0L
        var q = deciBinary
        var i = 0
        do {
            result += (q % 10) * 2.0.pow(i++.toDouble()).toInt()
            q /= 10
        } while (q > 0)
        return result
    }

    fun printAsDecimal(deciBinary: Long) {
        println(toDecimal(deciBinary))
    }
}
