package enum

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

fun sample() {
    val apple: Fruit2 = Fruit2.APPLE
    println(apple) // 🍎
    println(apple.producer) // 👨‍🌾
    apple.printSimilarFruit() // 🍏
}
