package samples.enum

private enum class BLT {
    BACON,
    LETTUCE,
    TOMATO
}

fun bltSampleUsage() {
    val bacon: BLT = BLT.valueOf("BACON")
    println(bacon)
}