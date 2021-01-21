package enum

private enum class RGB(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    companion object {
        fun from(value: Int): RGB = values().first { it.value == value }
        fun fromOrNull(value: Int): RGB? = values().firstOrNull { it.value == value }
    }
}

object RGBExamples {
    fun example() {
        val red: RGB = RGB.valueOf("RED")
        println(red) // RED

        val green: RGB = RGB.from(0x00FF00)
        println(green) // GREEN

        val nil: RGB? = RGB.fromOrNull(0xFFFFF)
        println(nil) // null
    }
}
