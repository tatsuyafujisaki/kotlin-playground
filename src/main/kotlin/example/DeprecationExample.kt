package example

object DeprecationExample {
    @Deprecated(
            message = "Use myNewFunction(String) instead.",
            replaceWith = ReplaceWith(
                    expression = "myNewFunction(String)",
                    imports = ["com.github.tatsuyafujisaki.androidplayground.myNewFunction"]
            ),
    )
    fun myOldFunction() {
    }

    fun myNewFunction(s: String) {
        println(s)
    }
}
