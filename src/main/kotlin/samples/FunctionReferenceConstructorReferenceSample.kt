package samples

fun main() {
    val xs: List<Int> = listOf(1, 2)

    // Function reference
    val ys: List<Int> = xs.filter(::isOdd) // [true, false]

    // Qualified function reference
    val zs2: List<String> = xs.map(Int::toString) // ["1", "2"]

    // Constructor reference
    val zs: List<Sample> = xs.map(::Sample) // [Sample(1), Sample(2)]
}

fun isOdd(x: Int) = x % 2 != 0

data class Sample(val x: Int)