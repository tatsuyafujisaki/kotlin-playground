package util

import java.net.URLEncoder

object UrlUtil {
    /**
     * Instead, use Uri.encode in Android.
     * https://developer.android.com/reference/kotlin/android/net/Uri#encode
     */
    private fun encode(url: String) = URLEncoder.encode(url, Charsets.UTF_8.name())

    fun getNthLevelDomain(domain: String, level: Int) =
            domain.split(".").takeLast(level).joinToString(".")
}
