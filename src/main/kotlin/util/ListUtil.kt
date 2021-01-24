package util

import java.util.Collections

object ListUtil {
    fun MutableList<Int>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
    
    fun <T> List<T>.rotate(distance: Int): List<T> {
        with(toList()) {
            Collections.rotate(this, distance)
            return this
        }
    }
}
