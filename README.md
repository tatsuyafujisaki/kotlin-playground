# Official style guides

* https://developer.android.com/kotlin/style-guide
* https://kotlinlang.org/docs/reference/coding-conventions.html

# Iterable

## How to iterate through an Iterable with index

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
for ((i, x) in xs.withIndex()) println("$i $x")

// Alternatively
xs.forEachIndexed { i, x -> println("$i $x") }

// Alternatively
xs.mapIndexed { i, x -> "$i $x" }.forEach(::println)
```

## How to filter an Iterable by index

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c').filterIndexed { i, _ -> i != 1 } // [a, c]
```

## How to iterate two iterables in parallel

```kotlin
// > The returned list has length of the shortest collection.
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/zip.html
fun <T> showZipExample(xs: List<T>, ys: List<T>) {
    xs.zip(ys).forEach { (x, y) ->
        println("$x$y")
    }
}

fun main() {
    showZipExample(listOf("A", "B"), listOf("a", "b")) // Aa Bb
    showZipExample(listOf("A", "B", "C"), listOf("a", "b")) // Aa Bb
    showZipExample(listOf("A", "B"), listOf("a", "b", "c")) // Aa Bb
}
```

# Array

## Type mapping between Kotlin and Java

 Kotlin      | Java      
-------------|-----------
 IntArray    | int[]     
 Array\<Int> | Integer[] 

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

## How to convert an IntArray an to Array\<Int>

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
val ys: Array<Int> = xs.toTypedArray()
```

## How to convert an Array\<Int> to an IntArray

```kotlin
val xs: Array<Int> = arrayOf(1, 2, 3)
val ys: IntArray = xs.toIntArray()
```

## How to iterate through an IntArray in reverse order

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
for (i in xs.lastIndex downTo 0) println(xs[i]) // 3 2 1
```

## How to iterate through an Array\<T> in reverse order

```kotlin
val xs: Array<String> = arrayOf("a", "b", "c")
for (i in xs.lastIndex downTo 0) println(xs[i]) // c b a
```

## How to deep-copy an IntArray

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
val ys: IntArray = xs.clone()
```

## How to deep-copy an Array\<T>

```kotlin
val xs: Array<String> = arrayOf("a", "b", "c")
val ys: Array<String> = xs.clone()
```

# List

## How to create a List

```kotlin
val a: List<Int> = emptyList() // equivalent of listOf(). [].
val b: List<Int> = listOf(1, 2, 3) // [1, 2, 3]
val c: List<Int> = listOfNotNull(1, null, 2) // [1, 2]
val d: List<Int> = List(3) { it } // The lambda is not optional. [0, 1, 2]
val e: List<String> = List(3) { "item$it" } // ["item0", "item1", "item2"]
```

## How to get the size of a List

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val size: Int = xs.size // 3. simpler than xs.count().
```

## How to iterate through a List in reverse order

```kotlin
val xs = listOf("a", "b", "c")
for (i in xs.lastIndex downTo 0) println(i) // 2 1 0
```

## How to add/remove an element to/from a List

```kotlin
 val xs: List<Int> = listOf(1, 2, 3)
val result1: List<Int> = xs.plus(4) // [1, 2, 3, 4]
val result2: List<Int> = xs.minus(2) // [1, 3]
```

## How to use `onEach` and `forEach`

```kotlin
listOf('a', 'b', 'c')
    .onEach { println(it) } // a b c
    .map(Char::uppercaseChar)
    .forEach { println(it) } // A B C
```

## How to filter a List

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
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
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<Int> = listOf(2, 3, 4)
val result1: List<Int> = xs + ys // [1, 2, 3, 2, 3, 4]
val result2: Set<Int> = xs union ys // [1, 2, 3, 4]
val result3: Set<Int> = xs intersect ys // [2, 3]
val result4: Set<Int> = xs subtract ys // [1]
```

## How to sort a List

```kotlin
data class Sample(val name: String)
```

```kotlin
val xs: List<Int> = listOf(1, 3, 2).sorted() // [1, 2, 3]
val ys: List<Int> = listOf(1, 3, 2).sortedDescending() // [3, 2, 1]
val zs: List<Sample> = listOf(
    Sample("c"),
    Sample("b"),
    Sample("a")
).sortedBy { it.name } // [Sample(name=a), Sample(name=b), Sample(name=c)]
```

## How to count the appearance of elements

```kotlin
val occurrences: Map<String, Int> =
    listOf("b", "c", "b", "c", "a", "c").groupingBy { it }.eachCount() // {a=1, b=2, c=3}
```

## How to convert a List? to a List

```kotlin
val xs: List<Int>? = null
val ys: List<Int> = x.orEmpty()
```

## How to convert a List to a Map

```kotlin
// data class Fruit(val name: String, val price: Int)
val fruits: List<Fruit> = listOf(Fruit(name = "apple", price = 100), Fruit(name = "orange", price = 200))
val result: Map<String, Int> = fruits.associate { it.name to it.price } // {apple=100, orange=200}
```

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
val result: Map<Char, Int> = xs.withIndex().associate { it.value to it.index } // {a=0, b=1, c=2}
```

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
val result: Map<Char, Char> = xs.associateWith { it.uppercaseChar() } // {a=A, b=B, c=C}
```

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
val result: Map<Char, Char> = xs.associateBy { it.uppercaseChar() } // {A=a, B=b, C=c}
```

```kotlin
val xs: List<String> = listOf("fruit" to "apple", "fruit" to "orange", "vegetable" to "carrot", "vegetable" to "potato")
val result: Map<String, List<String>> =
    xs.groupBy({ it.first }, { it.second }) // {fruit=[apple, orange], vegetable=[carrot, potato]}
```

```kotlin
val xs: List<String> = listOf("key1", "key2", "key1", "key2")
val result: Map<String, List<Int>> =
    xs.mapIndexed { i, x -> x to i }.groupBy({ it.first }, { it.second }) // {key1=[0, 2], key2=[1, 3]}
```

## How to convert two List(s) to a List

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

## How to convert a List to a Pair

```kotlin
val pair: Pair<String, String> = listOf("a", "b", "c").zipWithNext().first() // (a, b)
```

## How to deep-copy a MutableList

```kotlin
val xs: MutableList<String> = mutableListOf("a", "b")
val ys: MutableList<String> = xs.toMutableList()
ys[1] = "c"
println(xs) // [a, b]
println(ys) // [a, c]
```

## How to destruct a List to values

```kotlin
val (a, b) = listOf("a", "b", "c")
```

# Set

## How to create a Set

```kotlin
val set1: Set<String> = setOf("a", "b", "b") // [a, b]
val set2: Set<String> = setOfNotNull(null, "a", "b", "b", null) // [a, b]
```

## How to convert a List to a SortedSet

```kotlin
enum class Fruit {
    APPLE, ORANGE
}

fun main() {
    val xs: SortedSet<String> = listOf("c", "b", "a").toSortedSet()
    println(xs) // [a, b, c]

    val ys: SortedSet<Fruit> = listOf(Fruit.ORANGE, Fruit.APPLE).toSortedSet(compareBy { it.ordinal })
    println(ys) // [APPLE, ORANGE]
}
```

## SortedSet
### How to sort a Set
```kotlin
println(sortedSetOf("10", "1", "2")) // [1, 10, 2]
println(sortedSetOf(comparator = reverseOrder(), "10", "1", "2")) // [2, 10, 1]
println(sortedSetOf(comparator = compareBy { it.toInt() }, "10", "1", "2")) // [1, 2, 10]
```

### How to convert a Set to a SortedSet
```kotlin
val set = setOf("10", "1", "2")
println(set.toSortedSet()) // [1, 10, 2]
println(set.toSortedSet(comparator = reverseOrder())) // [2, 10, 1]
println(set.toSortedSet(comparator = compareBy { it.toInt() })) // [1, 2, 10]
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
val result6: String = map.getOrDefault("d", "not found") // not found
val result7: String = map.getOrElse("d") { "not found" } // not found
```

## How to map a Map

```kotlin
val map: Map<Char, Int> = mapOf('a' to 10, 'b' to 20)
val result1: Map<Char, Int> = map.mapKeys { it.key.uppercaseChar() } // {A=10, B=20}
val result2: Map<Char, Int> = map.mapValues { it.value * 10 } // {a=100, b=200}
val result3: Map<Char, Int> = map.map { it.key.uppercaseChar() to it.value * 10 }.toMap() // {A=100, B=200}
```

## How to filter a Map

```kotlin
val map: Map<Char, Char> = mapOf('A' to 'a', 'B' to 'b')
val result1: Map<Char, Char> = map.filterKeys { it == 'A' } // {A=a}
val result2: Map<Char, Char> = map.filterValues { it == 'b' } // {B=b}
val result3: Map<Char, Char> = map.filter { it.key == 'B' && it.value == 'b' } // {B=b}
```

## How to flatten a list of Map(s)

```kotlin
val map1: Map<Char, Char> = mapOf('A' to 'a')
val map2: Map<Char, Char> = mapOf('B' to 'b')
val maps: List<Map<Char, Char>> = listOf(map1, map2)
val map: Map<Char, Char> = maps.reduce { acc, x -> acc + x } // {A=a, B=b}
```

## SortedMap
### How to sort a Map by key
```kotlin
println(sortedMapOf("10" to "ten", "1" to "one", "2" to "two")) // {1=one, 10=ten, 2=two}
println(sortedMapOf(comparator = reverseOrder(), "10" to "ten", "1" to "one", "2" to "two")) // {2=two, 10=ten, 1=one}
println(sortedMapOf(comparator = compareBy { it.toInt() }, "10" to "ten", "1" to "one", "2" to "two")) // {1=one, 2=two, 10=ten}
```

### How to sort a Map by value
```kotlin
val map = mapOf("ten" to "10", "one" to "1", "two" to "2")
println(map.toList().sortedBy { it.second }.toMap()) // {one=1, ten=10, two=2}
println(map.toList().sortedByDescending { it.second }.toMap()) // {two=2, ten=10, one=1}
println(map.toList().sortedBy { it.second.toInt() }.toMap()) // {one=1, two=2, ten=10}
```

### How to convert a Map to a SortedMap
```kotlin
val map = mapOf("10" to "ten", "1" to "one", "2" to "two")
println(map.toSortedMap()) // {1=one, 10=ten, 2=two}
println(map.toSortedMap(reverseOrder())) // {2=two, 10=ten, 1=one}
println(map.toSortedMap(compareBy { it.toInt() })) // {1=one, 2=two, 10=ten}
```

# String

## How to convert a String? to a String

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

## How to get a substring

```kotlin
val s1: String = "a/b/c".substringBefore("/") // a
val s2: String = "a/b/c".substringAfter("/") // b/c
val s3: String = "a/b/c".substringBeforeLast("/") // a/b
val s4: String = "a/b/c".substringAfterLast("/") // c
```

## How to remove a substring

```kotlin
val s1: String = "abc".removePrefix("a") // bc
val s2: String = "abc".removeSuffix("c") // ab
val s3: String = "aaa".removeSurrounding("a") // a
val s4: String = "abc".removeSurrounding("a", "c") // b
```

## How to use joinToString(...)

```kotlin
val xs: String = listOf("a", "b", "c").joinToString(
    separator = ";",
    prefix = "{",
    postfix = "}",
    limit = 2,
    truncated = "..."
) {
    it.uppercaseChar()
} // "{A;B;...}
```

## How to append strings

```kotlin
val s: String = buildString {
    appendLine("aaa")
    appendLine("bbb")
}
```

# Enum

```kotlin
enum class MyColor(val value: Int) {
    BLACK(0x000000), WHITE(0xffffff), UNKNOWN(Int.MIN_VALUE);

    companion object {
        // Verbose for illustrative purposes
        fun fromOrdinal(ordinal: Int): MyColor = entries[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): MyColor? = entries.getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): MyColor = entries.getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): MyColor = valueOf(name)
        fun fromNameIgnoreCase(name: String): MyColor = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): MyColor? = entries.find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): MyColor? = entries.find { it.name == name.uppercase() }
        fun fromValue(value: Int): MyColor = entries.first { it.value == value }
        fun fromValueOrNull(value: Int): MyColor? = entries.find { it.value == value }
    }
}
```

## Enum with properties and functions

```kotlin
enum class Fruit {
    APPLE {
        override fun toString() = "🍎"
        override val producer: String = "👨‍🌾"
        override fun printSimilarFruit() {
            println("🍏")
        }
    },
    ORANGE {
        override fun toString() = "🍊"
        override val producer: String = "👩‍🌾"
        override fun printSimilarFruit() {
            println("🍋")
        }
    };

    abstract val producer: String
    abstract fun printSimilarFruit()
}

println(apple) // 🍎
println(apple.producer) // 👨‍🌾
apple.printSimilarFruit() // 🍏
```

## How to extend an enum class

```
enum class Fruit(val emoji: String) {
    APPLE("🍎"),
    ORANGE("🍊");
}

enum class ExtendedFruit(emoji: String) {
    APPLE(Fruit.APPLE.emoji),
    ORANGE(Fruit.ORANGE.emoji),
    BANANA("🍌");
}
```

# Function references / Constructor references

```kotlin
fun main() {
    val xs: List<Int> = listOf(1, 2)

    // Function reference
    val result1: List<Int> = xs.filter(::isOdd) // [true, false]

    // Qualified function reference
    val result2: List<String> = xs.map(Int::toString) // ["1", "2"]

    // Companion object function reference
    val result3: List<Int> = xs.map((Sample)::double) // [2, 4]
    val result4: List<Int> = xs.map(Sample.Companion::double) // [2, 4]

    // Constructor reference
    val result5: List<Sample> = xs.map(::Sample) // [Sample(1), Sample(2)]
}

fun isOdd(x: Int): Boolean = x % 2 != 0

data class Sample(val x: Int) {
    companion object {
        fun double(x: Int): Int {
            return x * 2
        }
    }
}
```

# How do I know when to use a sealed class or interface?

Prefer sealed interfaces to sealed classes, except in these two cases.

## Case 1: You want to use a constructor. (i.e. you want to hardcode a common property)

Using the constructor of a sealed class is more concise than ...

```kotlin
sealed class Fruit(val emoji: String)
data class Apple(val foo: String) : Fruit(emoji = "🍎")
data class Orange(val bar: String) : Fruit(emoji = "🍊")
```

... using a sealed interface.

```kotlin
sealed interface Fruit {
    val emoji: String
}

data class Apple(val foo: String, override val emoji: String = "🍎") : Fruit
data class Orange(val bar: String, override val emoji: String = "🍊") : Fruit
```

## Case 2: You want to use generics, which interfaces don't support.

You can use generics with a sealed class, as shown below.

```kotlin
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val throwable: Throwable) : UiState<Nothing>()
}
```

On the other hand, you cannot use generics with a sealed interface as shown below.

```kotlin
private sealed interface NonGenericUiState {
    data object Loading : NonGenericUiState
    data class Success(val data: String) : NonGenericUiState
    data class Failure(val throwable: Throwable) : NonGenericUiState
}
```

# `<reified T>` (Reified type parameters)

enables you to use `is` and `as` on `T`.

```kotlin
inline fun <reified T> f(a: Any) {
    if (a is T) {
        // Do something
    }
    val t = a as T
}
```

https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters

# Delegate

## How to use Delegates.observable(...)

```kotlin
// The type can be omitted.
var observed: String by Delegates.observable("a") { _, old, new ->
    if (old == new) return@observable // In case you only want to react to the value change.
    println("$old -> $new")
}
println(observed) // "a"
observed = "b" // "a -> b"
observed = "b" // (none)
observed = "c" // "b -> c"
```

## How to use Delegates.vetoable(...)

```kotlin
// The type can be omitted.
var evenNumber: Int by Delegates.vetoable(1) { _, old, new ->
    new % 2 == 0
}

println(evenNumber) // 1. Though 1 is not an even number, the initial value is not validated.
evenNumber = 3 // vetoed because 3 is not a even number.
println(evenNumber) // 1
evenNumber = 2
println(evenNumber) // 2
```

## Comparison among `lazy`, `Delegates.notNull()`, and `lateinit`

 &nbsp;       | lazy        | Delegates.notNull() | lateinit                      
--------------|-------------|---------------------|-------------------------------
 val          | supported   | not supported       | not supported                 
 non-nullable | supported   | supported           | not supported primitive types |supported|supported|not supported performance|ok(※1)|ok(
 ※1)          | good Dagger | supported           | supported(※2)                 |supported

* (※1) A delegate object is created.
* (※2) Dagger's field injection is not supported but Dagger's method (setter) injection is.

# Regex

> For pattern syntax reference see [Pattern](https://developer.android.com/reference/java/util/regex/Pattern).

https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/

# How to print the name of the current function

```kotlin
fun sample() {
    println(object {}.javaClass.enclosingMethod?.name) // sample
}
```

# How to print when a property is accessed

```kotlin
var x = ""
    get() {
        println("${object {}.javaClass.enclosingMethod?.name}: $field")
        return field
    }
    set(value) {
        println("${object {}.javaClass.enclosingMethod?.name}: $value")
        field = value
    }
```

# How to get the name of the current thread

```kotlin
println(Thread.currentThread().name)

// alternatively
println(currentCoroutineContext())
```

* The preceding code also shows the name of the current coroutine after the following preparation:
    * IntelliJ IDEA > Menu bar > Run > Edit Configurations > Configurations > VM options >
      add `-Dkotlinx.coroutines.debug`.

# Syntactic sugar for throwing exceptions

 Function           | Throws                   
--------------------|--------------------------
 check(Boolean)     | IllegalStateException    
 checkNotNull(T?)   | IllegalStateException    
 error(Any)         | IllegalStateException    
 require(Boolean)   | IllegalArgumentException 
 requireNotNull(T?) | IllegalArgumentException 

# `fold` versus `reduce`

 &nbsp; | Type of initial value | Type of element of input array | Return type               
--------|-----------------------|--------------------------------|---------------------------
 fold   | R                     | T                              | R                         
 reduce | n/a                   | T                              | T or one of T's ancestors 

* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/reduce.html

# KDoc

## How to add an external link

```kotlin
/**
 * @see <a href="https://example.com">Example</a>
 */
```

Markdown also works. However, Intellij IDEA issues a warning that "Example" in `[Example]` is invalid.

```kotlin
/**
 * @see [Example](https://example.com)
 */
```

https://github.com/Kotlin/dokka/issues/518#issuecomment-744062184

# Best practices

[best-practices.md](markdown/best-practices.md)

# Coroutines and Flow

[coroutines-flow.md](markdown/coroutines-flow.md)

# ktlint

[ktlint.md](markdown/ktlint.md)

# Misc

[misc.md](markdown/misc.md)

# RxJava

[rxjava.md](markdown/rxjava.md)

# Scope functions

[scope-functions.md](markdown/scope-functions.md)
