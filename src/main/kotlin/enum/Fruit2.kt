package enum

/** @sample Fruit2Samples */
enum class Fruit2 {
    APPLE {
        override fun toString() = "🍎"
        override val producer: String = "👨‍🌾"
        override fun printSimilarFruit() {
            println("🍏")
        }
    },
    ORANGE {
        override fun toString() = "🍊"
        override val producer: String = "👩‍🌾"
        override fun printSimilarFruit() {
            println("🍋")
        }
    };

    abstract val producer: String
    abstract fun printSimilarFruit()
}
