package enum

enum class Fruit {
    APPLE, ORANGE, UNKNOWN;

    companion object {
        fun fromOrdinalOrThrow(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): Fruit = values().getOrElse(ordinal) { UNKNOWN }
        fun fromNameOrThrow(name: String): Fruit = valueOf(name)
        fun fromNameIgnoreCaseOrThrow(name: String): Fruit = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): Fruit? = values().find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): Fruit? = values().find { it.name == name.uppercase() }
    }
}
