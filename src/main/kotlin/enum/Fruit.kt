package enum

enum class Fruit {
    APPLE {
        override val price: Int = 100
        override fun getEmoji() = "🍎"
    },
    ORANGE {
        override val price: Int = 200
        override fun getEmoji() = "🍊"
    };

    abstract val price: Int
    abstract fun getEmoji(): String
}

private fun main() {
    for (fruit in Fruit.entries) {
        println("price: ${fruit.price}")
        println("emoji: ${fruit.getEmoji()}")
    }
}
