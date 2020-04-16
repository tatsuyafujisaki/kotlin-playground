# Best practices
* Use `repeat(n)` rather than `while(n--)` for simplicity.
* Use `xs.lastIndex` rather than `xs.indices.last` or `xs.size - 1` for cimplicity.
* Use `copyRangeOf(...)` rather than `sliceArray(...)` for simplicity.
* Use `foo.takeIf { it.bar == baz }` rather than `if (foo.bar == baz) foo else null` for simplicity.
* Use `foo.takeUnless { it.bar == baz }` rather than `if (foo.bar != baz) foo else foo` for simplicity.
* Use `intArray.toCollection(mutableList)` rather than `mutableList.addAll(intArray.toTypedArray())` for simplicity.
* Use `error()` rather than `throw IllegalStateException()` for simplicity.
* Mark a function with `suspend` rather than call a coroutine builder inside the function.

# Difference between intArrayOf() and arrayOf()
```kotlin
intArrayOf(1, 2, 3) // IntArray (int[] in Java)
arrayOf(1, 2, 3) // Array<Int> (Integer[] in Java)
```

# How to initialize IntArray (int[] in Java) only with size.
```kotlin
val xs = IntArray(size)
```
Note that there is no way to initialize Array<Int> (Integer[] in Java) only with size.

# How to convert IntArray (int[] in Java) to Array<Int> (Integer[] in Java)
```kotlin
intArray.toTypedArray()
```

# How to sort a list
```kotlin
list.sorted()
list.sortedDescending()
```

# How to sort a map by key
```kotlin
map.toSortedMap()
```

# How to show the name of the current thread and the name of the current coroutine
* `Thread.currentThread().name`
  * shows the name of the current thread without condition.
  * shows the name of the current coroutine after the following preparation:
    * IntelliJ IDEA > Menu bar > Run > Edit Configurations > Configurations > VM options > add `-Dkotlinx.coroutines.debug`.
