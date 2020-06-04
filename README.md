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
val xs: IntArray = intArrayOf(1, 2, 3) // [1, 2, 3]
val ys: IntArray = IntArray(3) // [0, 0, 0]
val zs: IntArray = IntArray(3) { it * 2 } // [0, 2, 4]
```

# How to create an Array\<T>
```kotlin
val xs: Array<Int> = arrayOf(1, 2, 3) // [1, 2, 3]

// The lambda is not optional.
val ys: Array<Int> = Array(3) { it } // [0, 1, 2]
val zs: Array<String> = Array(3) { "item$it" } // ["item0", "item1", "item2"]
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
val xs: List<Int> = listOf(1, 2, 3) // [1, 2, 3]
val ys: List<Int> = listOfNotNull(1, null, 2) // [1, 2]

// The lambda is not optional.
val xs: List<Int> = List(3) { it } // [0, 1, 2]
val ys: List<String> = List(3) { "item$it" } // ["item0", "item1", "item2"]
```

# How to sort a List
```kotlin
val xs: List<Int> = listOf(1, 3, 2).sorted() // [1, 2, 3]
val ys: List<Int> = listOf(1, 3, 2).sortedDescending() // [3, 2, 1]
```

# How to create a Map
```kotlin
val map: Map<Int, String> = mapOf(1 to "a", 2 to "b", 3 to "c").withDefault { "default" } // {1=a, 2=b, 3=c}
val x: String = map.getValue(4) // "default"
val y: String? = map[4] // null
```

# How to sort a Map
```kotlin
val map: Map<Int, String> = mapOf(1 to "a", 3 to "b", 2 to "c").toSortedMap() // {1=x, 2=y, 3=z}
```

# How to convert String? to String
```kotlin
val x: String? = null
val y: String = x.orEmpty()
```

# How to convert List\<T>? to List\<T>
```kotlin
val xs: List<Int>? = null
val ys: List<Int> = x.orEmpty()
```

# Difference between CharSequence.isBlank() and CharSequence.isEmpty()
```kotlin
val blank: String = " "
val x: Boolean = blank.isBlank() // true
val y: Boolean = blank.isEmpty() // false
```

# How to extract a double-quoted URL from a string
```kotlin
val html: String = "<a href=\"https://example.com\"><img src=\"sample.png\"></a>"
val url: String = Regex("(https?://.*?)\"").find(s)?.groupValues?.last() // https://example.com
```

# How to show the name of the current thread and the name of the current coroutine
* `Thread.currentThread().name`
  * shows the name of the current thread without condition.
  * shows the name of the current coroutine after the following preparation:
    * IntelliJ IDEA > Menu bar > Run > Edit Configurations > Configurations > VM options > add `-Dkotlinx.coroutines.debug`.

# How to use function references and constructor references
```kotlin
fun main() {
    val xs: List<Int> = listOf(1, 2)

    // Function reference
    val ys: List<Int> = xs.filter(::isOdd) // [true, false]

    // Qualified function reference
    val zs2: List<String> = xs.map(Int::toString) // ["1", "2"]

    // Constructor reference
    val zs: List<Sample> = xs.map(::Sample) // [Sample(1), Sample(2)]
}

fun isOdd(x: Int): Boolean = x % 2 != 0

data class Sample(val x: Int)
```

# Regex
Construct|Matches|Greedy or reluctant
--|--|--
x?|0,1|greedy
x??|0,1|reluctant
x*|0+|greedy
x*?|0+|reluctant
x+|1+|greedy
x+?|1+|reluctant

## References
* Kotlin's regex pattern syntax is the same as Java's [Pattern class](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/regex/Pattern.html).

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
