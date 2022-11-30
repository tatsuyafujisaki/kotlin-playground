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

# Declaration-site variance

* Invariant
    * is a type parameter without the "in" or "out" modifier.
    * If a type parameter is invariant, the exact same type is required.
* Covariant
    * is a type parameter with the "out" modifier.
    * can only be returned (produced) and never referenced (consumed).
    * can return a subtype.
        * Producer<out Dog>
        * If a signature of a function is supposed to return a Dog, it can return a Pug but not an Animal.
* Contravariant
    * is a type parameter with the "in" modifier.
    * can only be referenced (consumed) and never returned (produced).
    * can receive a subtype.
        * Consumer<in Dog>
        * If a signature of a function is supposed to receive a Dog, it can receive a Pug but not an Animal.
* https://kotlinlang.org/docs/reference/generics.html#declaration-site-variance

# @Volatile

is normally used in a database instance.

```
@Volatile
private var INSTANCE: MyDatabase? = null
```

* Volatile fields are thread-safe. They are never cached, and all writes and reads will be done to and from the main
  memory. It means that changes made by one thread to the field are immediately made visible to other threads.
* The increment operator "++" in JVM languages is not thread-safe even when the field is volatile because "++" is not an
  atomic operation because it consists of a read, an increment, and a write.
* https://kotlinlang.org/docs/shared-mutable-state-and-concurrency.html#volatiles-are-of-no-help
    * > Volatiles are of no help. There is a common misconception that making a variable volatile solves concurrency problem.
* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-volatile/
