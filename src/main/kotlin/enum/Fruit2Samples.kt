package enum

object Fruit2Samples {
    fun sample() {
        val apple: Fruit2 = Fruit2.APPLE
        println(apple) // 🍎
        println(apple.producer) // 👨‍🌾
        apple.printSimilarFruit() // 🍏
    }
}
