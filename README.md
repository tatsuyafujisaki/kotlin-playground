# Best practices
* Use `Collection<T>.size` rather than `Collection<T>.count()` for simplicity.
* Use `repeat(n)` rather than `while(n--)` for simplicity.
* Use `xs.lastIndex` rather than `xs.indices.last` or `xs.size - 1` for simplicity.
* Use `xs.ifEmpty { "default" }` rather than `xs.takeIf { it.isNotEmpty() } ?: "default"` simplicity.
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

# How to create an IntArray
```kotlin
val xs = intArrayOf(1, 2, 3) // [1, 2, 3]

val ys = IntArray(3) // [0, 0, 0]

val zs = IntArray(3) { it * 2 } // [0, 2, 4]
```

# How to create an Array\<Int> or Array\<String>
```kotlin
val xs = arrayOf(1, 2, 3) // [1, 2, 3]

// No constructor takes only size.
val xs = Array(3) { it } // [0, 1, 2]

val xs = Array(3) { "item$it" } // ["item0", "item1", "item2"]
```

# How to convert IntArray to Array\<Int>
```kotlin
val xs: Array<Int> = intArray.toTypedArray()
```

# How to convert Array\<Int> to IntArray
```kotlin
val xs: IntArray = arrayInt.toIntArray()
```

# How to create a List
```kotlin
val xs = listOf(1, 2, 3) // [1, 2, 3]
val xs = listOfNotNull(1, null, 2) // [1, 2]

// No constructor takes only size.
val xs = List(3) { it } // [0, 1, 2]

val xs = List(3) { "item$it" } // ["item0", "item1", "item2"]
```

# How to sort a List
```kotlin
val sorted = xs.sorted()

val sortedDescending = xs.sortedDescending()
```

# How to sort a Map by key
```kotlin
val sorted = map.toSortedMap()
```

# How to convert String? to String
```kotlin
val s1: String? = null
val s2: String = s1.orEmpty()
```

# How to convert List\<T>? to List\<T>
```kotlin
val xs: List<Int>? = null
val ys: List<Int> = xs.orEmpty()
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
