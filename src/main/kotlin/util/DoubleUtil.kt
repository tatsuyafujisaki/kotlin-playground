package util

object DoubleUtil {
    private fun getBigInteger(x: Double) = x.toBigDecimal().toBigInteger()
    private fun roundFormat(x: Double, decimals: Int) = "%.${decimals}f".format(x)
}
