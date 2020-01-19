package util.io

import java.io.File

// Usage of SimulatedStandardInput
fun main() {
    val stdin = SimulatedStandardInput("input.txt")
    fun readLine() = stdin.readLine()
}

/** @param pathname a sibling of the src folder */
class SimulatedStandardInput(pathname: String) {
    private var lines = File(pathname).readLines()
    private var i = 0
    fun readLine() = lines[i++]
}
