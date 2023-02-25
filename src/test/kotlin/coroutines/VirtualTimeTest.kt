package coroutines

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource
import kotlin.time.measureTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
class VirtualTimeTest {
    @Test
    fun coroutineTest() = runTest {
        var x = "a"
        launch {
            x = "b"
            delay(1.seconds)
            x = "c"
        }
        assertEquals("a", x)
        advanceTimeBy(1)
        assertEquals("b", x)
        advanceTimeBy(1_000)
        assertEquals("c", x)
    }

    /**
     * [Delay-skipping](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-test.html)
     */
    @Test
    fun measureTimeTest() = runTest {
        val elapsed = TimeSource.Monotonic.measureTime {
            val job = launch {
                delay(1.minutes) // skipped
                withContext(Dispatchers.Default) {
                    delay(5.seconds) // not skipped because Dispatchers.Default does not know about TestCoroutineScheduler.
                }
            }
            job.join()
        }
        println(elapsed) // about five seconds
    }
}
