package util

import kotlin.math.max

object StringUtil {
    fun alphabetize(s: String) = s.toCharArray().sorted().joinToString("")
    fun equalsIgnoreCase(s1: String, s2: String) = s1.equals(other = s2, ignoreCase = true)
    fun replaceAt(s: String, index: Int, replacement: Char) = s.replaceRange(startIndex = index, endIndex = index + 1, replacement = replacement.toString())

    fun getSubstrings(s: String) = sequence {
        for (i in s.indices) {
            for (j in i + 1..s.length) yield(s.substring(i, j))
        }
    }

    fun isAllSameChars(s: String) = if (s.isEmpty()) true else s.toSet().size == 1

    /** https://en.wikipedia.org/wiki/Longest_common_subsequence_problem */
    private fun findLongestCommonSubsequence(s1: String, s2: String): String {
        fun splitLast(s: String) = s.substringBeforeLast("") to s.substringAfterLast("")

        if (s1.isEmpty() || s2.isEmpty()) return ""
        val (s1a, s1last) = splitLast(s1)
        val (s2a, s2last) = splitLast(s2)
        if (s1last == s2last) return findLongestCommonSubsequence(s1a, s2a) + s1last
        val lcs1 = findLongestCommonSubsequence(s1, s2a)
        val lcs2 = findLongestCommonSubsequence(s1a, s2)
        return if (lcs1.length > lcs2.length) lcs1 else lcs2
    }

    /** https://en.wikipedia.org/wiki/Longest_common_subsequence_problem */
    fun findLongestCommonSubsequenceLength(s1: String, s2: String) = List(s1.length + 1) {
        IntArray(s2.length + 1)
    }.also {
        for (i in s1.indices) {
            for (j in s2.indices) it[i + 1][j + 1] = if (s1[i] == s2[j]) it[i][j] + 1 else max(it[i + 1][j], it[i][j + 1])
        }
    }[s1.length][s2.length]

    fun permute(s: String): List<String> {
        fun permute(s: String, result: String = ""): List<String> = if (s.isEmpty()) {
            listOf(result)
        } else {
            s.flatMapIndexed { i, c ->
                permute(s.removeRange(i, i + 1), result + c)
            }
        }
        return permute(s)
    }
}
