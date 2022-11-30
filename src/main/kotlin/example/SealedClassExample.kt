package example

sealed class Fruit(val name: String) {
    val common: String = "This is a common property."
}

data class Apple(val appleBrand: String) : Fruit("I am an apple.")
data class Orange(val orangeBrand: String) : Fruit("I am an orange.")

fun main() {
    val apple = Apple("Fuji")
    val orange = Orange("Unshu")
    println(apple)
    println(apple.common)
    println(orange)
    println(orange.common)
}
