# Module
is a set of Kotlin files compiled together.

# Kotlin Standard Library
is the Java Standard Library + extension functions for built-in classes such as `String` and `Collection`.

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