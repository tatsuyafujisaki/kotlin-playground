package util

import java.util.Collections

object ListUtil {
    fun <T> List<T>.rotate(distance: Int): List<T> {
        with(toList()) {
            Collections.rotate(this, distance)
            return this
        }
    }
}
