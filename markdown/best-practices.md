# Use A rather than B for simplicity or clarity

 A                                                        | B                                                                          | Note                                             
----------------------------------------------------------|----------------------------------------------------------------------------|--------------------------------------------------
 `with(...)`                                              | `run(...)`                                                                 | if the receiver is not nullable.                 
 `emptyList`<br>`emptySet`<br>`emptyMap`<br>`emptyFlow`|`listOf`<br>`setOf`<br>`mapOf`<br>`flowOf`                                                     
 `buildList`<br>`buildSet`<br>`buildMap`|`mutableListOf`<br>`mutableSetOf`<br>`mutableMapOf`
 `repeat(n) { println(it) }`                              | `for (i in 0..<n) { println(i) }`<br>
 `Iterable<T>.find(...)`                                  | `Iterable<T>.firstOrNull(...)`                                             | `find` is an alias of `firstOrNull`.   
 `Collection<T>.size`<br>`String.length`|`Collection<T>.count()`<br>`String.count()`
 `String.substring(...)`|`String.take(...)`
 `Set<T>`                                                 | `List<T>`                                                                  | if elements are unique and unordered. Don't overuse `List<T>`.
 `Iterable<T>.forEachIndexed { i, x -> ... }`             | `Iterable<T>.forEach { ... }`                                              | if you need to access both indices and elements. 
 `nullableString.orEmpty()`<br>`nullableList.orEmpty()`   | `nullableString ?: ""`<br>`nullableList ?: emptyList()`                  
 `CharSequence.lastIndex`<br>`List<T>.lastIndex`          | `String.length - 1`<br>`List<T>.size - 1`                                
 `List<Int>.sumBy {...}` or `List<Int>.sumByDouble {...}` | `List<Int>.map {...}.sum()`                                                
 `(this.)javaClass.simpleName`                            | `this::class.java.simpleName`                                              
 `Flow.filterNotNull()`                                   | `Flow.flatMapConcat { if (it != null) flowOf(it) else emptyFlow() }`       

## Prefer infix notation
Recommended|Not recommended
--|--
`in`<br>`!in`|`contains`
`A to B`|`Pair(A, B)`                                   

# Date and time
Recommended|Not recommended
--|--
`kotlin.time.Duration`|`java.time.Duration`
`DateTimeFormatter.ISO_LOCAL_DATE`|`DateTimeFormatter.ofPattern("yyyy-MM-dd")`
`DateTimeFormatter.ISO_LOCAL_TIME`|`DateTimeFormatter.ofPattern("HH:mm:ss")`

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
