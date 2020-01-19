package samples

fun main() {
    val functionLiteralWithReceiver: String.() -> String = {
        this.toUpperCase()
    }

    val normalFunction: (String) -> String = {
        it.toUpperCase()
    }

    // Example 1. You can use a function-literal-with-receiver as an extension function.
    println("aaa".functionLiteralWithReceiver()) // AAA

    // Example 2. You can use a function-literal-with-receiver as a normal function.
    println(functionLiteralWithReceiver("bbb")) // BBB

    // Example 3. However, if a normal function meets your needs, just write it for simplicity as follows.
    println(normalFunction("ccc")) // CCC

    // Example 4. You can pass a lambda as a function-literal-with-receiver to a function.
    println(myFunction1a { this.toUpperCase() }) // DDD
    println(myFunction1b { this.toUpperCase() }) // DDD

    // Example 5. If a normal function meets your needs, just write it for simplicity as follows.
    println(myFunction2 { it.toUpperCase() }) // EEE
}

fun myFunction1a(functionLiteralWithReceiver: String.() -> String) = "ddd".functionLiteralWithReceiver()
fun myFunction1b(functionLiteralWithReceiver: String.() -> String) = functionLiteralWithReceiver("ddd")
fun myFunction2(normalFunction: (String) -> String) = normalFunction("eee")