package example

sealed class Shape(val description: String) {
    val common: String = "common!"
}

data class Circle(val radius: Int) : Shape("circle!")
data class Square(val perimeter: Int) : Shape("square!")

fun main() {
    val circle = Circle(1)
    val square = Square(1)
    println(circle) // Circle(radius=1)
    println(circle.common) // shape!
    println(circle.description) // common!
    println(square) // Square(perimeter=1)
    println(square.common) // common!
    println(square.description) // square!
}
