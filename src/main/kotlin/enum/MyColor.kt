package enum

enum class MyColor(val value: Int) {
    BLACK(0x000000), WHITE(0xffffff), UNKNOWN(Int.MIN_VALUE);

    companion object {
        // Verbose for illustrative purposes
        fun fromOrdinal(ordinal: Int): MyColor = entries[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): MyColor? = entries.getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): MyColor = entries.getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): MyColor = valueOf(name)
        fun fromNameIgnoreCase(name: String): MyColor = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): MyColor? = entries.find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): MyColor? = entries.find { it.name == name.uppercase() }
        fun fromValue(value: Int): MyColor = entries.first { it.value == value }
        fun fromValueOrNull(value: Int): MyColor? = entries.find { it.value == value }
    }
}
