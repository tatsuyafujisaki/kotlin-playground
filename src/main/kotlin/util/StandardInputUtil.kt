package util

object StandardInputUtil {
    fun readIntegers() = readLine().orEmpty().split(' ').filterNot { it.isEmpty() }.map { it.toInt() }
    fun readDoubles() = readLine().orEmpty().split(' ').filterNot { it.isEmpty() }.map { it.toDouble() }
}
