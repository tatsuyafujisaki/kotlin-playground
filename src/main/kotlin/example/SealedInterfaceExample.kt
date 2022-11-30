package example

sealed interface Shape2 {
    val description: String
}

data class Circle2(val radius: Int) : Shape2 {
    override val description = "circle's description"
}

data class Square2(val perimeter: Int) : Shape2 {
    override val description = "square's description"
}

fun main() {
    val circle = Circle2(1)
    val square = Square2(1)
    println(circle)
    println(circle.description)
    println(square)
    println(square.description)
}
