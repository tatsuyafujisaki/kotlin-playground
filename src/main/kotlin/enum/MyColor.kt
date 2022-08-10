package enum

enum class MyColor(val value: Int) {
    BLACK(0x000000), WHITE(0xffffff), UNKNOWN(Int.MIN_VALUE);

    companion object {
        fun fromOrdinal(ordinal: Int): MyColor = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): MyColor? = values().getOrNull(ordinal)
        fun fromOrdinalWithDefault(ordinal: Int): MyColor = values().getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): MyColor = valueOf(name)
        fun fromNameIgnoreCase(name: String): MyColor = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): MyColor? = values().find { it.name == name }
        fun fromNameOrNullIgnoreCase(name: String): MyColor? = values().find { it.name == name.uppercase() }
        fun fromValue(value: Int): MyColor = values().first { it.value == value }
        fun fromValueOrNull(value: Int): MyColor? = values().find { it.value == value }
    }
}
