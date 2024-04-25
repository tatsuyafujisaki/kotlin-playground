package util

import java.net.URLEncoder

object UrlUtil {
    fun encode(url: String) = URLEncoder.encode(url, Charsets.UTF_8.name())

    /**
     * [MatchResult.groupValues] returns a list of two elements.
     * The first element is the entire match including a trailing double quotation mark we don't want.
     * The second element is the first match, which is what we want.
     */
    fun extractDoubleQuotedUrl(s: String) = Regex("(https?://.+?)\"").find(s)?.groupValues?.last()
}

private fun main() {
    val html = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
    val url = UrlUtil.extractDoubleQuotedUrl(html) // https://example.com
    println(url)
}
