package samples.enum

private enum class RGB(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    companion object {
        fun from(value: Int) = values().firstOrNull { it.value == value }

        fun example() {
            val red = valueOf("RED")
            println(red)

            val green = from(0x00FF00)
            println(green)

            val nil = from(0xFFFFFF)
            println(nil)
        }
    }
}