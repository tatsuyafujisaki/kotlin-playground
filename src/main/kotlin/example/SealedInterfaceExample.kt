package example

sealed interface Shape {
    val description: String
    val onClick: () -> Unit
}

data class Circle(
    val radius: Int,
    override val description: String,
    override val onClick: () -> Unit
) : Shape

data class Square(
    val perimeter: Int,
    override val description: String,
    override val onClick: () -> Unit
) : Shape

fun main() {
    val circle = Circle(1, "circle!") { println("Circle is clicked.") }
    val square = Square(1, "square!") { println("Square is clicked.") }
    println(circle) // Circle(radius=1, description=circle!, onClick=() -> kotlin.Unit)
    circle.onClick() // Circle is clicked.
    println(square) // Square(perimeter=1, description=square!, onClick=() -> kotlin.Unit)
    square.onClick() // Square is clicked.
}
