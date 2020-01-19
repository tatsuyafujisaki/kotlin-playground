package enum

enum class Fruit {
    APPLE,
    ORANGE;

    companion object {
        fun valueOfOrNull(value: String): Fruit? = values().find { it.name == value }
        fun from(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
    }
}
