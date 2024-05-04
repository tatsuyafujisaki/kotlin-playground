package enum

enum class Vegetable(emoji: String) {
    Lettuce(emoji = "🥬") {
        override val price: Int = 100
        override fun doSomething() {
            println("Lettuce!")
        }
    },
    Tomato(emoji = "🍅") {
        override val price: Int = 200
        override fun doSomething() {
            println("Tomato!")
        }
    };

    abstract val price: Int
    abstract fun doSomething()
}
