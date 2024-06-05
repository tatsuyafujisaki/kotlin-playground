package example

object DeprecationExample {
    @Deprecated(
            message = "Use myNewFunction(String) instead.",
            replaceWith = ReplaceWith(
                    expression = "myNewFunction(String)",
                    imports = ["example.DeprecationExample"]
            ),
    )
    fun myOldFunction() {
    }

    fun myNewFunction(s: String) {
        println(s)
    }
}
