package util.text

object JapaneseUtil {
    private fun japaneseNumeral(n: Int, isOnesPlace: Boolean) = when (n) {
        1 -> if (isOnesPlace) "一" else ""
        2 -> "二"
        3 -> "三"
        4 -> "四"
        5 -> "五"
        6 -> "六"
        7 -> "七"
        8 -> "八"
        9 -> "九"
        else -> ""
    }

    private fun japaneseNumerals(n: Int): String {
        val largeNumbers = listOf("", "十", "百", "千")
        val s = n.toString()
        var result = ""

        for (i in s.indices) {
            result += japaneseNumeral(s[i].digitToInt(), i == s.lastIndex)
            if (s[i] != '0') result += largeNumbers[s.lastIndex - i]
        }

        return result
    }
}
