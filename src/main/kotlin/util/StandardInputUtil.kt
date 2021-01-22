package util

object StandardInputUtil {
    fun readIntegers() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toInt)
    fun readDoubles() = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toDouble)
}
