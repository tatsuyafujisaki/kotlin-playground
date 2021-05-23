package enum

enum class RGB(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    companion object {
        fun from(value: Int): RGB = values().first { it.value == value }
        fun fromOrNull(value: Int): RGB? = values().firstOrNull { it.value == value }
    }
}
