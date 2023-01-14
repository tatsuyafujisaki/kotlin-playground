import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MyJUnitTest {
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
        val value = ""
        assertTrue(value is String)
    }

    @Test
    fun assertContainsTest() {
        val iterable = listOf("a", "b", "c")
        val element = "b"
        assertTrue(element in iterable)
    }
}
