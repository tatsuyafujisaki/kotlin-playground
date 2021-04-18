package util

import java.io.File

/**
 * Usage:
 * val stdin = SimulatedStandardInput("input.txt")
 * fun readLine() = stdin.readLine()
 *
 * @param pathname is expected to exist as the src folder's sibling.
 */
class SimulatedStandardInput(pathname: String) {
    private var lines = File(pathname).readLines()
    private var i = 0
    fun readLine() = lines[i++]
}
