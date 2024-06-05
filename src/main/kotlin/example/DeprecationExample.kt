package example

object DeprecationExample {
    @Deprecated(
        message = "Use myNewFunction(String) instead.",
        // The deprecated declaration is replaced by the following expression when saving a file
        // if Settings > "Actions on Save" > "Run code cleanup" is enabled.
        replaceWith = ReplaceWith(expression = "myNewFunction(String)"),
    )
    fun myOldFunction(s: String) {
        println(s)
    }

    fun myNewFunction(s: String) {
        println(s)
    }
}

// private fun main() {
//     myOldFunction("Hello")
// }
