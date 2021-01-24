package util

object DoubleUtil {
    /** Redundant wrapper */
    fun Double.roundFormat(decimals: Int) = "%.${decimals}f".format(this)
}
