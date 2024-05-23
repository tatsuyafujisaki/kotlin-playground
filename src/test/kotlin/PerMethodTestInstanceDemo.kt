import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PerMethodTestInstanceDemo {
    private var b = false

    @Test
    fun assertTrueTest1() {
        b = true
        assertTrue(b)
    }

    @Test
    fun assertFalseTest2() {
        // Although b is set to true in assertTrueTest1(),
        // b is false here because a new instance of the test class is created before each test method is executed.
        assertFalse(b)
    }
}
