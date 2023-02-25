package coroutines

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
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
        advanceTimeBy(1_000)
        assertEquals("b", x)
        advanceTimeBy(1)
        assertEquals("c", x)
    }
}
