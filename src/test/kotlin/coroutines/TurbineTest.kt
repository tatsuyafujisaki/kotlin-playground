package coroutines

import app.cash.turbine.test
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.minutes

class TurbineTest {
    @Test
    fun test() = runTest {
        flowOf("a", "b").test {
            assertEquals("a", awaitItem())
            assertEquals("b", awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun test2() = runTest {
        flow {
            emit("a")
            delay(1.minutes) // skipped
            emit("b")
        }.test {
            assertEquals("a", awaitItem())
            assertEquals("b", awaitItem())
            awaitComplete()
        }
    }
}
