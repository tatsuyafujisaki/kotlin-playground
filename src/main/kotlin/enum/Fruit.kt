package enum

enum class Fruit {
    APPLE, ORANGE, UNKNOWN;

    companion object {
        // Verbose for illustrative purposes
        fun fromOrdinal(ordinal: Int): Fruit = entries[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): Fruit? = entries.getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): Fruit = entries.getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): Fruit = valueOf(name)
        fun fromNameIgnoreCase(name: String): Fruit = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): Fruit? = entries.find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): Fruit? = entries.find { it.name == name.uppercase() }
    }
}
