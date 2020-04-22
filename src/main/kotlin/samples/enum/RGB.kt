package samples.enum

private enum class RGB(val value: String) {
    RED("Red!"),
    GREEN("Green!"),
    BLUE("Blue!")
}

fun rgbSampleUsage() {
    val red: RGB = RGB.valueOf("RED")
    println(red)
    println(red.value)
}