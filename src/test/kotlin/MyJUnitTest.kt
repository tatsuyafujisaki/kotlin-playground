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
        val x: String? = null
        assertNull(x)
    }

    @Test
    fun assertIsTest() {
        val value = ""
        assertTrue(value is String)
    }

    @Test
    fun assertContainsTest() {
        val range = listOf("a", "b", "c")
        val value = "b"
        assertTrue(value in range)
    }
}
