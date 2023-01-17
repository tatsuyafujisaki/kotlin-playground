import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class MyTruthTest {
    @Test
    fun assertTrueTest() {
        val actual = true
        assertThat(actual).isTrue()
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
    fun assertEqualsTest() {
        val expected = "a"
        val actual = "a"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun assertCollectionContainsTest() {
        val iterable = listOf("a", "b", "c")
        val element = "b"
        assertThat(element).isIn(iterable)
    }

    @Test
    fun assertStringContainsTest() {
        val actual = "abc"
        val substring = "b"
        assertThat(actual).contains(actual)
    }
}
