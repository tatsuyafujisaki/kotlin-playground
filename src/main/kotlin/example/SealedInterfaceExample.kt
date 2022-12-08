package example

sealed interface Shape {
    val description: String
    val print: () -> Unit
}

data class Circle(
    val radius: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape

data class Square(
    val perimeter: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape

fun main() {
    val circle = Circle(1, "circle!") { println("circle!") }
    val square = Square(1, "square!") { println("square!") }
    println(circle)
    circle.print()
    println(square)
    square.print()
}
