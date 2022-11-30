package example

object Law {
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

    /**
     * Print a list of article numbers (条番号) to be imported as a dictionary into Google Japanese Input.
     * The dictionary will help look up the articles of the Constitution of Japan.
     *
     * [The Constitution of Japan](https://www.ndl.go.jp/constitution/etc/j01.html)
     */
    fun printArticleNumbers() {
        for (i in 1..103) {
            println("$i\t第${i}条\t名詞")
        }
    }

    /**
     * Print a list of article numbers (条番号) to be imported as a dictionary into Google Japanese Input.
     * The dictionary will help look up the articles of the Civil Code of Japan.
     *
     * [The Civil Code of Japan](https://elaws.e-gov.go.jp/document?lawid=129AC0000000089_20200401_501AC0000000034)
     */
    fun printJapaneseArticleNumbers() {
        for (i in 1..1049) {
            println("$i\t第${japaneseNumerals(i)}条\t名詞")
        }
    }
}
