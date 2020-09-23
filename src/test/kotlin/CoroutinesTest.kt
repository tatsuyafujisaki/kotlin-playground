import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

class CoroutinesTest {
    @Test
    @ExperimentalCoroutinesApi
    fun test() = runBlockingTest {
        val startTime = currentTime

        launch {
            delay(1000)
        }.join()

        delay(1000)

        println(currentTime - startTime) // 2000
    }
}
