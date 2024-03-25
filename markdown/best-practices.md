# Use A rather than B for simplicity or clarity
A|B|Note
--|--|--
`kotlin.time.Duration`|`java.time.Duration`
`with(...)`|`run(...)`|if the receiver is not nullable.
`A to B`|`Pair(A, B)`
`emptyList()`|`listOf()`
`emptyFlow()`|`flowOf()`
`in`|`contains`
`repeat(n)`|`while(n--)`
`repeat(n) { println(it) }`|`for (i in 0 until n) { println(i) }`
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
    
# Tips
- Decompose a pair if possible.
    - Recommended: `listOf("apple" to 100, "orange" to 200).map { it.first + it.second })`
    - Not recommended: `listOf("apple" to 100, "orange" to 200).map { (fruit, price) -> fruit + price }`
- When defining a function, make the parameter types as abstract as possible.
  - e.g. Use a type as left as possible.
    - Iterable > Collection > (Set) > List.
- Mark a function with `suspend` if you need to call another suspend function in it, but cannot access `CoroutineScope`.
- Don't overuse `List<T>`. Use `Set<T>` if items are unique and unordered.
