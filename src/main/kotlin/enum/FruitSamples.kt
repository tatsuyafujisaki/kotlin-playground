package enum

import util.io.StandardOutputUtil.printHorizontally

object FruitSamples {
    fun sample() {
        val apple1: Fruit = Fruit.valueOf("APPLE")
        println(apple1) // APPLE

        val apple2: Fruit = Fruit.from(0)
        println(apple2) // APPLE

        val nil: Fruit? = Fruit.fromOrNull(-1)
        println(nil) // null

        val fruits: Array<Fruit> = Fruit.values()
        fruits.printHorizontally() // APPLE ORANGE
    }
}
