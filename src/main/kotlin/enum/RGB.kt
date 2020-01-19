package enum

enum class RGB(val value: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    companion object {
        fun valueOfOrNull(value: String): RGB? = values().find { it.name == value }
        fun from(value: Int): RGB = values().first { it.value == value }
        fun fromOrNull(value: Int): RGB? = values().find { it.value == value }
    }
}
