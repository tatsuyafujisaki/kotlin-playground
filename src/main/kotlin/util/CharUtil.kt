package util

object CharUtil {
    val Char.alphabeticalIndex get() = this - 'a'

    /**
     * For a competitive programming platform that does not support Char.digitToInt() of Kotlin 1.4.30
     * '1'.toInt() -> 49
     * '1'.digitToInt() -> 1
     */
    fun Char.digitToInt() = toString().toInt()
}
