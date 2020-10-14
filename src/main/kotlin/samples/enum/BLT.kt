package samples.enum

enum class BLT {
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
            val bacon = valueOf("BACON")
            println(bacon)
        }

        fun example2() {
            for (value in values()) {
                println("The index of " + value + " is " + value.ordinal)
            }
        }
    }
}