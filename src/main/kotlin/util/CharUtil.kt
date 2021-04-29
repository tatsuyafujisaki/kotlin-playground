package util

object CharUtil {
    val Char.alphabeticalIndex get() = this - 'a'

    /**
     * '1'.toInt() -> 49
     * '1'.digitToInt() -> 1
     */
    @Deprecated(
        message = "Use Char.digitToInt() in Kotlin 1.4.30+. A Java alternative is Character.getNumericValue().",
        replaceWith = ReplaceWith("Char.digitToInt()")
    )
    fun Char.myDigitToInt() = toString().toInt()
}
