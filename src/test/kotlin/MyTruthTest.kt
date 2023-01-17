import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class MyTruthTest {
    @Test
    fun assertTrueTest() {
        val actual = true
        assertThat(actual).isTrue()
    }

    @Test
    fun assertEqualsTest() {
        val expected = "a"
        val actual = "a"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun assertNullTest() {
        val actual: String? = null
        assertThat(actual).isNull()
    }

    @Test
    fun assertIsTest() {
        val actual = ""
        assertThat(actual).isInstanceOf(String::class.java)
    }

    @Test
    fun assertContainsTest() {
        val iterable = listOf("a", "b", "c")
        val actual = "b"
        assertThat(actual).isIn(iterable)
    }
}
