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
    };

    companion object {
        fun example() {
            val bacon: BLT = valueOf("BACON")
            println(bacon)
        }
    }
}

