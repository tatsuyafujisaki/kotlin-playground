# Module
is a set of Kotlin files compiled together.

# Function type "(A.(B) -> C)"
* is called `a function literal with receiver`
* https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver

# Kotlin.Result
* Android Studio says `'Kotlin.Result' cannot be used as a return type.`.
* https://github.com/Kotlin/KEEP/blob/master/proposals/stdlib/result.md#limitations

# Interoperability between Kotlin and Java
Java sees functions defined in Kotlin only through bytecode.

# Int.MAX_VALUE
* 10^9 (1,000,000,000) < `Int.MAX_VALUE` (2,147,483,647) < 10^10 < (10,000,000,000)
* This knowledge is necessary in competitive programming

# Stream
## Hot stream
* is like a touchless faucet.
* has all the pains of resource management (once you open it, you must not forget to close it)
  * https://medium.com/@elizarov/reactive-streams-and-kotlin-flows-bfd12772cda4

## Cold stream
* is like a touchless faucet.
* provides an elegant relief of this chore.
  * https://medium.com/@elizarov/reactive-streams-and-kotlin-flows-bfd12772cda4
