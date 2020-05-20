# Best practices
* Use `Collection<T>.size` rather than `Collection<T>.count()` for simplicity.
* Use `repeat(n)` rather than `while(n--)` for simplicity.
* Use `xs.lastIndex` rather than `xs.indices.last` or `xs.size - 1` for simplicity.
* Use `xs.ifEmpty { "default" }` rather than `xs.takeIf { it.isNotEmpty() } ?:"default"` simplicity.
* Use `s.ifBlank { "default" }` rather than `s.takeIf { it.isNotBlank() } ?: "default"` simplicity.
* Use `foo.takeIf { it.bar == baz }` rather than `if (foo.bar == baz) foo else null` for simplicity.
* Use `foo.takeUnless { it.bar == baz }` rather than `if (foo.bar != baz) foo else null` for simplicity.
* Use `copyRangeOf(...)` rather than `sliceArray(...)` for simplicity.
* Use `intArray.toCollection(mutableList)` rather than `mutableList.addAll(intArray.toTypedArray())` for simplicity.
* Use `error()` rather than `throw IllegalStateException()` for simplicity.
* Mark a function with `suspend` rather than call a coroutine builder inside the function.

# Type mapping between Kotlin and Java
Kotlin|Java
---|---
IntArray|int[]
Array\<Int>|Integer[]

# Difference between intArrayOf() and arrayOf()
```kotlin
intArrayOf(1, 2, 3) // IntArray (int[] in Java)
arrayOf(1, 2, 3) // Array<Int> (Integer[] in Java)
```

# How to initialize IntArray (int[] in Java) only with size
```kotlin
val xs = IntArray(size)
```

# How to initialize Array<Int> (Integer[] in Java) only with size
There is no way to do it.

# How to convert IntArray (int[] in Java) to Array<Int> (Integer[] in Java)
```kotlin
intArray.toTypedArray()
```

# How to sort a list
```kotlin
list.sorted()
list.sortedDescending()
```

# How to convert List\<T>? to List\<T>
```kotlin
val xs: List<Int>? = null
val ys: List<Int> = xs.orEmpty()
```    

# How to sort a map by key
```kotlin
map.toSortedMap()
```

# How to convert String? to String
```kotlin
val s1: String? = null
val s2: String = s1.orEmpty()
```

# Difference between CharSequence.isBlank() and CharSequence.isEmpty()
```kotlin
val s = " "
s.isBlank() // true
s.isEmpty() // false
```

# How to show the name of the current thread and the name of the current coroutine
* `Thread.currentThread().name`
  * shows the name of the current thread without condition.
  * shows the name of the current coroutine after the following preparation:
    * IntelliJ IDEA > Menu bar > Run > Edit Configurations > Configurations > VM options > add `-Dkotlinx.coroutines.debug`.

# How to remove comments
1. Command+Shift+F in IntelliJ IDEA.
2. Check `Regex`.
3. Put one of the following regexes.
4. Click `Replace All`.

## Regex to match all the comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?)\*/
```

## Regex to match copyright comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?Copyright[\S\s]+?)\*/
```

## Regex to match copyright comments in XML
```
<!--([\S\s]+?Copyright[\S\s]+?)-->
```
