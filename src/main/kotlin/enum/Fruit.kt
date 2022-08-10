package enum

enum class Fruit {
    APPLE, ORANGE, UNKNOWN;

    companion object {
        fun fromOrdinal(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
        fun fromOrdinalWithDefault(ordinal: Int): Fruit = values().getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): Fruit = valueOf(name)
        fun fromNameIgnoreCase(name: String): Fruit = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): Fruit? = values().find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): Fruit? = values().find { it.name == name.uppercase() }
    }
}
