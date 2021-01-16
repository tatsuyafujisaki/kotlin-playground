package util

import java.util.Collections

fun <T> List<T>.rotate(distance: Int): List<T> {
    with(toList()) {
        Collections.rotate(this, distance)
        return this
    }
}
