package example

sealed class Shape2(open val description: String, open val print: () -> Unit) {
    val commonDescription: String = "common!"
    val printCommon: () -> Unit = { println("common!") }
}

data class Circle2(
    val radius: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape2("circle!", print)

data class Square2(
    val perimeter: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape2("square!", print)

fun main() {
    val circle = Circle2(1, "circle!") { println("circle!") }
    val square = Square2(1, "square!") { println("square!") }
    println(circle)
    println(circle.commonDescription)
    circle.printCommon()
    circle.print()
    println(square)
    println(square.commonDescription)
    square.printCommon()
    square.print()
}
