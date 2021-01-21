package enum

enum class Fruit2 {
    APPLE {
        override val color: String = "Red"
        override fun toString() = "🍎"
        override fun printSimilarFruit() {
            println("🍏")
        }
    },
    ORANGE {
        override val color: String = "Orange"
        override fun toString() = "🍊"
        override fun printSimilarFruit() {
            println("🍋")
        }
    };

    abstract val color: String
    abstract fun printSimilarFruit()
}

object Fruit2Examples {
    fun example() {
        val apple: Fruit2 = Fruit2.APPLE
        println(apple.color) // Red
        apple.printSimilarFruit() // 🍏
    }
}
