# Use A rather than B for simplicity or clarity
A|B|Note
--|--|--
`Int.abs(...)`|`Int.absoluteValue`
`A to B`|`Pair(A, B)`
`emptyList()`|`listOf()`
`repeat(n)`|`while(n--)`
`repeat(n) { println(it) }`|`for (i in 0 until n) { println(i) }`
`Iterable<T>.find(...)`|`Iterable<T>.firstOrNull(...)`|`find()` is an alias of `firstOrNull(...)`.
`Collection<*>.size`|`Collection<*>.count()`
`Set<T>`|`List<T>`|if elements are unique and unordered.
`Iterable<T>.forEachIndexed { i, x -> ... }`|`Iterable<T>.forEach { ... }`|if you access both indices and elements.
`nullableList.orEmpty()`<br>`nullableString.orEmpty()`|`nullableList ?: emptyList()`<br>`nullableString ?: ""`
`with(...)`|`run(...)`|if the receiver is not nullable.
`filterIsInstance<Foo>()`|`filterIsInstance(Foo::class.java)`
infix notation(e.g. `x !in xs`)|`!xs.contains(x)`
`List<*>.lastIndex`<br>`CharSequence.lastIndex`|`List<*>.size - 1`<br>`String.length - 1`
`List<Int>.sumBy {...}` or `List<Int>.sumByDouble {...}`|`List<Int>.map {...}.sum()`

# Tips
* When define a function, make the types of parameters as abstract as possible.
  * e.g. Use a type as left as possible.
    * Iterable > Collection > (Set) > List.
* Don't overuse List\<T>. Use Set\<T> if the order does not matter and the elements are unique.
* Mark a function with `suspend` rather than call a coroutine builder inside the function.
