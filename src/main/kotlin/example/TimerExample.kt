package example

import java.util.Timer
import kotlin.concurrent.schedule

private fun main() {
    Timer().schedule(3_000) {
        println("Hello")
    }
}
