package enum

enum class Fruit {
    APPLE,
    ORANGE;

    companion object {
        fun fromName(name: String): Fruit? = values().find { it.name == name }
        fun fromOrdinal(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
    }
}
