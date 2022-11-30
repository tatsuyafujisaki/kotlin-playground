package example

sealed interface Shape2 {
    val description: String
}

data class Circle2(val radius: Int) : Shape2 {
    override val description = "circle!"
}

data class Square2(val perimeter: Int) : Shape2 {
    override val description = "square!"
}

fun main() {
    val circle = Circle2(1)
    val square = Square2(1)
    println(circle) // Circle2(radius=1)
    println(circle.description) // circle!
    println(square) // Square2(perimeter=1)
    println(square.description) // square!
}
