package enum

object RGBSamples {
    fun sample() {
        val red: RGB = RGB.valueOf("RED")
        println(red) // RED

        val green: RGB = RGB.from(0x00FF00)
        println(green) // GREEN

        val nil: RGB? = RGB.fromOrNull(0xFFFFF)
        println(nil) // null
    }
}
