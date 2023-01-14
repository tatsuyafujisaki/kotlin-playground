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
        val x: String? = null
        assertThat(x).isNull()
    }

    @Test
    fun assertIsTest() {
        val value = ""
        assertThat(value).isInstanceOf(String::class.java)
    }

    @Test
    fun assertContainsTest() {
        val iterable = listOf("a", "b", "c")
        val value = "b"
        assertThat(value).isIn(iterable)
    }
}
