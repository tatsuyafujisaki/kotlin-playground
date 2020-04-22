package samples.enum

private enum class RGB(val value: Int) {
    RED(0xff0000),
    GREEN(0x00ff00),
    BLUE(0x0000ff)
}

fun rgbSampleUsage() {
    val red: RGB = RGB.valueOf("RED")
    println(red)
    println("%x".format(red.value))
}