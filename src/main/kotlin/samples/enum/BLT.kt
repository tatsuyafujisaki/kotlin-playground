package samples.enum

private enum class BLT {
    BACON {
        override fun toString() = "Bacon!"
    },
    LETTUCE {
        override fun toString() = "Lettuce!"
    },
    TOMATO {
        override fun toString() = "Tomato!"
    },
}

fun bltSampleUsage() {
    val bacon: BLT = BLT.valueOf("BACON")
    println(bacon)
}