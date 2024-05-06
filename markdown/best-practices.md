# Use A rather than B for simplicity or clarity
A|B|Note
--|--|--
`kotlin.time.Duration`|`java.time.Duration`
`with(...)`|`run(...)`|if the receiver is not nullable.
`A to B`|`Pair(A, B)`
`emptyList()`|`listOf()`
`emptyFlow()`|`flowOf()`
`in`|`contains`
`repeat(n) { println(it) }`|`for (i in 0 until n) { println(i) }`<br>or<br>`while(i--) { println(i) }`
`Iterable<T>.find(...)`|`Iterable<T>.firstOrNull(...)`|`find(...)` is an alias of `firstOrNull(...)`.
`Collection<*>.size`|`Collection<*>.count()`
`Set<T>`|`List<T>`|if elements are unique and unordered.
`Iterable<T>.forEachIndexed { i, x -> ... }`|`Iterable<T>.forEach { ... }`|if you need to access both indices and elements.
`nullableList.orEmpty()`<br>`nullableString.orEmpty()`|`nullableList ?: emptyList()`<br>`nullableString ?: ""`
`filterIsInstance<Foo>()`|`filterIsInstance(Foo::class.java)`
`x in xs`<br>`x !in xs`|`xs.contains(x)`<br>`!xs.contains(x)`
`List<*>.lastIndex`<br>`CharSequence.lastIndex`|`List<*>.size - 1`<br>`String.length - 1`
`List<Int>.sumBy {...}` or `List<Int>.sumByDouble {...}`|`List<Int>.map {...}.sum()`
`(this.)javaClass.simpleName`|`this::class.java.simpleName`
`Flow.filterNotNull()`|`Flow.flatMapConcat { if (it != null) flowOf(it) else emptyFlow() }`

# Use [onEach](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/on-each.html) or [onEachIndexed](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/on-each-indexed.html) when you want to perform a side effect for clarity
For example, the following is better ...
```kotlin
val capitals = listOf('a', 'b', 'c')
        .onEach { println(it) } // a b c
        .map { it.uppercaseChar() }
```
... than the following.
```kotlin
val capitals = listOf('a', 'b', 'c')
        .map {
            println(it) // a b c
            it.uppercaseChar()
        }
```

# Misc
- Decompose a pair if possible.
    - Recommended: `listOf("apple" to "🍎", "orange" to "🍊").map { it.first + it.second })`
    - Not recommended: `listOf("apple" to "🍎", "orange" to "🍊").map { (fruit, emoji) -> fruit + emoji }`
- When defining a function, make the parameter types as abstract as possible.
  - e.g. Use a type as left as possible.
    - Iterable > Collection > (Set) > List.
- Mark a function with `suspend` if you need to call another suspend function in it, but cannot access `CoroutineScope`.
- Don't overuse `List<T>`. Use `Set<T>` if items are unique and unordered.
