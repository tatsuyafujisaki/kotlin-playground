package example

sealed class Shape2(open val description: String, open val onClick: () -> Unit) {
    val commonDescription: String = "common!"
    val onCommonClick: () -> Unit = { println("Clicked.") }
}

data class Circle2(
    val radius: Int,
    override val description: String,
    override val onClick: () -> Unit
) : Shape2("circle!", onClick)

data class Square2(
    val perimeter: Int,
    override val description: String,
    override val onClick: () -> Unit
) : Shape2("square!", onClick)

fun main() {
    val circle = Circle2(1, "circle!") { println("Circle is clicked.") }
    val square = Square2(1, "square!") { println("Square is clicked.") }
    println(circle) // Circle2(radius=1, description=circle!, onClick=() -> kotlin.Unit)
    println(circle.commonDescription) // Clicked.
    circle.onCommonClick() // Circle is clicked.
    circle.onClick() // Circle is clicked.
    println(square) // Square2(perimeter=1, description=square!, onClick=() -> kotlin.Unit)
    println(square.commonDescription) // common!
    square.onCommonClick() // Clicked.
    square.onClick() // Square is clicked.
}
