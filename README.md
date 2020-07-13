# Best practices
* Use `Collection<T>.size` rather than `Collection<T>.count()` for simplicity.
* Use `A to B` rather than `Pair(A, B)` for simplicity.
* Use `with(...)` rather than `run(...)` if the receiver is not nullable because, in my opinion, the former is slightly more readable.
* Use `filterIsInstance<Foo>()` rather than `filterIsInstance(Foo::class.java)` for simplicity.
* Use infix notation for simplicity as long as the usage follows the Kotlin coding convention.
  * e.g. Use `x in xs` rather than `xs.contains(x)`.
* Use `repeat(n)` rather than `while(n--)` for simplicity.
* Use `xs.lastIndex` rather than `xs.indices.last` or `xs.size - 1` for simplicity.
* Use `copyRangeOf(...)` rather than `sliceArray(...)` for simplicity.
* Use `intArray.toCollection(mutableList)` rather than `mutableList.addAll(intArray.toTypedArray())` for simplicity.
* Use `error()` rather than `throw IllegalStateException()` for simplicity.
* Mark a function with `suspend` rather than call a coroutine builder inside the function.

# Type mapping between Kotlin and Java
Kotlin|Java
---|---
IntArray|int[]
Array\<Int>|Integer[]

# Array
## How to create an IntArray
```kotlin
val xs: IntArray = intArrayOf(1, 2, 3) // [1, 2, 3]
val ys: IntArray = IntArray(3) // [0, 0, 0]
val zs: IntArray = IntArray(3) { it * 2 } // [0, 2, 4]
```

## How to create an Array\<T>
```kotlin
val xs: Array<Int> = arrayOf(1, 2, 3) // [1, 2, 3]

// The lambda is not optional.
val ys: Array<Int> = Array(3) { it } // [0, 1, 2]
val zs: Array<String> = Array(3) { "item$it" } // ["item0", "item1", "item2"]
```

## How to convert IntArray to Array\<Int>
```kotlin
val xs: Array<Int> = intArray.toTypedArray()
```

## How to convert Array\<Int> to IntArray
```kotlin
val xs: IntArray = arrayInt.toIntArray()
```

# List
## How to create a List
```kotlin
val xs: List<Int> = listOf(1, 2, 3) // [1, 2, 3]
val ys: List<Int> = listOfNotNull(1, null, 2) // [1, 2]

// The lambda is not optional.
val xs: List<Int> = List(3) { it } // [0, 1, 2]
val ys: List<String> = List(3) { "item$it" } // ["item0", "item1", "item2"]
```

## How to add/remove an element to/from a List
```kotlin
 val xs: List<Int> = listOf(1, 2 ,3)
 val result1: List<Int> = xs.plus(4) // [1, 2, 3, 4]
 val result2: List<Int> = xs.minus(2) // [1, 3]
```

## How to filter a List
```kotlin
val xs: List<Int> = listOf(1, 2 ,3)
val result1: List<Int> = xs.filter { it == 2 } // [2]
val result2: List<Int> = xs.filterNot { it == 2 } // [1, 3]
val ys: List<Int?> = listOf(1, null, 2)
val result3: List<Int> = ys.filterNotNull() // [1, 2]
val result4: List<Int> = ys.mapNotNull { it.takeIf { it == 2 } } // [2]
```

## ifEmpty() / takeIf() / takeUnless()
```kotlin
val x: List<Int> = emptyList<Int>().ifEmpty { listOf(42) } // 42
val y: List<Int>? = listOf(1, 2, 3).takeIf { 0 in it } // null
val z: List<Int>? = listOf(1, 2, 3).takeUnless { 1 in it } // null
```

## How to work with two List(s)
```kotlin
val xs: List<Int> = listOf(1, 2 ,3)
val ys: List<Int> = listOf(2, 3, 4)
val result1: List<Int> = xs + ys // [1, 2, 3, 2, 3, 4]
val result2: Set<Int> = xs union ys // [1, 2, 3, 4]
val result3: Set<Int> = xs intersect ys // [2, 3]
val result4: Set<Int> = xs subtract ys // [1]
```

## How to sort a List
```kotlin
val xs: List<Int> = listOf(1, 3, 2).sorted() // [1, 2, 3]
val ys: List<Int> = listOf(1, 3, 2).sortedDescending() // [3, 2, 1]
```

## How to convert a List? to a List
```kotlin
val xs: List<Int>? = null
val ys: List<Int> = x.orEmpty()
```

## How to convert a List to a Map
```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val result1: Map<Int, Int> = xs.withIndex().associate { it.value to it.index } // {1=0, 2=1, 3=2}
val result2: Map<Int, String> = xs.associateWith { "value$it" } // {1=value1, 2=value2, 3=value3}
val result3: Map<String, Int> = xs.associateBy { "key$it" } // {key1=1, key2=2, key3=3}
```

## How to convert two List(s) to another List
```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<String> = listOf("a", "b", "c")
val zs: List<String> = xs.zip(ys).map { (x, y) ->
    x.toString() + y
} // [1a, 2b, 3c]
```

## How to convert two List(s) to a Map
```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<String> = listOf("a", "b", "c")
val zs: Map<Int, String> = xs.zip(ys).toMap() // {1=a, 2=b, 3=c}
```

# Set
## How to convert a List to a SortedSet
```kotlin
fun main() {
    val xs: SortedSet<String> = listOf("c", "b", "a").toSortedSet() // [a, b, c]

    val ys: SortedSet<Sample> = listOf(
        Sample("a", 3),
        Sample("b", 2),
        Sample("c", 1)
    ).toSortedSet(compareBy { it.order }) // [(c,1), (b,2), (a,3)]

    println(xs)
}

class Sample(private val name: String, val order: Int) {
    override fun toString(): String {
        return "($name,$order)"
    }
}
```

# Map
## How to create a Map
```kotlin
val map: Map<String, String> = mapOf("a" to "x", "b" to "y", "c" to "z").withDefault { "default" }
val result1: Set<Map.Entry<String, String>> = map.entries // [a=x, b=y, c=z]
val result2: Set<String> = map.keys // [a, b, c]
val result3: Collection<String> = map.values // [x, y, z]
val result4: String? = map["d"] // null
val result5: String = map.getValue("d") // "default"
val result6: String? = map.getOrDefault("d", "not found") // not found
val result7: String? = map.getOrElse("d") { "not found" } // not found
```

## How to filter a Map
```kotlin
val map: Map<String, String> = mapOf("a" to "x", "b" to "y", "c" to "z").withDefault { "default" }
val result1 = map.filterKeys { it == "b" } // {b=y}
val result2 = map.filterValues { it == "y" } // {b=y}
val result3 = map.filter { it.key == "b" && it.value == "y" } // {b=y}
```

## How to sort a Map
```kotlin
val map: Map<String, String> = mapOf("a" to "x", "b" to "y", "c" to "z")
val result: Map<String, String> = map.toSortedMap() // {a=x, b=y, c=z}
```

# String
## How to convert String? to String
```kotlin
val x: String? = null
val y: String = x.orEmpty()
```

## isBlank() / isEmpty() / ifBlank() / ifEmpty()
```kotlin
val a: Boolean = " ".isBlank() // true
val b: Boolean = "".isEmpty() // true
val c: String = " ".ifBlank { "default" } // "default"
val d: String = "".ifEmpty { "default" } // "default"
```

## Remove a substring from a String
```kotlin
val result1: String = "abc".removePrefix("a")) // bc
val result2: String = "abc".removeSuffix("c")) // ab
val result3: String = "aaa".removeSurrounding("a")) // bb
val result4: String = "abc".removeSurrounding("a", "c")) // b
```

## How to use joinToString(...)
```kotlin
val xs: String = listOf("a", "b", "c").joinToString(
  separator = ";",
  prefix = "{",
  postfix = "}",
  limit = 2,
  truncated = "etc"
) // "{a;b;etc}
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

# How to use CookieManager
```kotlin
val cookieManager = CookieManager.getInstance()

val url1 = "example.com"
val url2 = "example.com/foo"
val url3 = "sub.example.com"
val url4 = "https://example.com"
val url5 = "http://example.com"

cookieManager.setCookie(url1, "a=1")
cookieManager.setCookie(url2, "b=2")
cookieManager.setCookie(url3, "c=3")
cookieManager.setCookie(url4, "d=4")
cookieManager.setCookie(url5, "e=5")
cookieManager.setCookie(url5, "e=5!")

val cookie1: String = cookieManager.getCookie(url1) // a = 1; b = 2; d = 4; e = 5!
val cookie2: String = cookieManager.getCookie(url2) // a = 1; b = 2; d = 4; e = 5!
val cookie3: String = cookieManager.getCookie(url3) // c = 3
val cookie4: String = cookieManager.getCookie(url4) // a = 1; b = 2; d = 4; e = 5!
val cookie5: String = cookieManager.getCookie(url5) // a = 1; b = 2; d = 4; e = 5!
```

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
