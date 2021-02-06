package util

object CharUtil {
    val Char.alphabeticalIndex: Int get() = this - 'a'

    /**
     * '1'.toInt() -> 49
     * '1'.toIntAsDigit() -> 1
     */
    fun Char.toIntAsDigit() = toString().toInt()
}
