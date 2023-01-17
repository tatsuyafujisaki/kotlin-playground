import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MyKotlinTest {
    @Test
    fun assertTrueTest() {
        val actual = true
        assertTrue(actual)
    }

    @Test
    fun assertEqualsTest() {
        val expected = "a"
        val actual = "a"
        assertEquals(expected, actual)
    }

    @Test
    fun assertNullTest() {
        val actual: String? = null
        assertNull(actual)
    }

    @Test
    fun assertIsTest() {
        val actual = ""
        assertIs<String>(actual)
    }

    @Test
    fun assertContainsTest() {
        val iterable = listOf("a", "b", "c")
        val element = "b"
        assertContains(iterable, element)
    }
}
