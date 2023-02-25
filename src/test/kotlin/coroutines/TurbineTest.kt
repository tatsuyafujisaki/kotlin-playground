package coroutines

import app.cash.turbine.test
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
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
            delay(10.seconds)
            emit("b")
        }.test {
            assertEquals("a", awaitItem())
            assertEquals("b", awaitItem())
            awaitComplete()
        }
    }
}
