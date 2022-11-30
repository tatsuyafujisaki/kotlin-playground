package example

sealed class Shape(val description: String) {
    val common: String = "shape's common description"
}

data class Circle(val radius: Int) : Shape("circle's description")
data class Square(val perimeter: Int) : Shape("square's description")

fun main() {
    val circle = Circle(1)
    val square = Square(1)
    println(circle)
    println(circle.common)
    println(square)
    println(square.common)
}
