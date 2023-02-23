package coroutines

import app.cash.turbine.test
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
}
