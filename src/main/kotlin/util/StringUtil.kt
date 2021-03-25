package util

import kotlin.math.max

object StringUtil {
    fun String?.isNeitherNullNorEmpty(): Boolean = this != null && isNotEmpty()
    fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
    fun String.splitLast() = if (lastIndex == 0) "" to first() else with(chunked(lastIndex)) { first() to last().last() }
    fun String.equalsIgnoreCase(s: String) = equals(s, true)
    fun String.alphabetized() = toCharArray().sorted().joinToString("")
    fun String.substrings() = sequence {
        for (i in indices) {
            for (j in i + 1..length) yield(substring(i, j))
        }
    }

    fun String.isAllSameChars(): Boolean {
        if (length == 0) return true
        val firstChar = first()
        for (i in 1 until length) if (this[i] != firstChar) return false
        return true
    }

    // https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
    fun lcs(s1: String, s2: String): String {
        if (s1.isEmpty() || s2.isEmpty()) return ""
        val (s1_, s1last) = s1.splitLast()
        val (s2_, s2last) = s2.splitLast()
        if (s1last == s2last) return lcs(s1_, s2_) + s1last
        val lcs1 = lcs(s1, s2_)
        val lcs2 = lcs(s1_, s2)
        return if (lcs1.length > lcs2.length) lcs1 else lcs2
    }

    // https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
    fun lcsLength(s1: String, s2: String) = Array(s1.length + 1) { IntArray(s2.length + 1) }.also {
        for (i in s1.indices) {
            for (j in s2.indices) it[i + 1][j + 1] = if (s1[i] == s2[j]) it[i][j] + 1 else max(it[i + 1][j], it[i][j + 1])
        }
    }[s1.length][s2.length]
}
