package util

import java.math.BigInteger

object DoubleUtil {
    /** Redundant wrapper */
    fun Double.roundFormat(decimals: Int) = "%.${decimals}f".format(this)

    fun Double.toBigInteger(): BigInteger = toBigDecimal().toBigInteger()
}
