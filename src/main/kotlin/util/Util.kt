package util

import kotlin.system.measureTimeMillis

object Util {
    fun sortByValue(map: Map<Any, Int>) = map.toList().sortedBy { it.second }.toMap()

    fun sortByValueDescending(map: Map<Any, Int>) = map.toList().sortedByDescending { it.second }.toMap()

    fun getEntryOfMaxValue(map: Map<Any, Int>) = map.maxByOrNull { it.value }

    fun printTimeMillis(f: () -> Unit) = println(measureTimeMillis(f))
}
