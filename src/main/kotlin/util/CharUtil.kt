package util

object CharUtil {
    val Char.alphabeticalIndex get() = this - 'a'
    val Char.digit get() = this - '0' // Use Char.digitToInt() in Kotlin 1.4.30+.
}
