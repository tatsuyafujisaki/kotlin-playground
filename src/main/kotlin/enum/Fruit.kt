package enum

import util.ArrayUtil.printHorizontally

enum class Fruit {
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

    companion object {
        fun from(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
    }
}

object FruitExamples {
    fun example() {
        val apple1: Fruit = Fruit.APPLE
        println(apple1.color) // Red
        apple1.printSimilarFruit() // 🍏

        val apple2: Fruit = Fruit.valueOf("APPLE")
        println(apple2) // Apple!

        val apple3: Fruit = Fruit.from(0)
        println(apple3) // Apple!

        val apple4: Fruit? = Fruit.fromOrNull(-1)
        println(apple4) // null

        val fruits: Array<Fruit> = Fruit.values()
        fruits.printHorizontally() // 🍎 🍊
    }
}
