package samples.enum

enum class Fruit(val value: Int) : SampleInterface {
    APPLE(100) {
        override val property1: String
            get() = "I am APPLE."

        override fun interfaceFunction(s: String) {
            println("APPLE and $s")
        }

        override fun function1(s: String) {
            println("APPLE and $s")
        }
    },
    ORANGE(200) {
        override val property1: String
            get() = "I am ORANGE."

        override fun interfaceFunction(s: String) {
            println("ORANGE and $s")
        }

        override fun function1(s: String) {
            println("ORANGE and $s")
        }
    };

    abstract val property1: String
    abstract fun function1(s: String)

    companion object {
        fun example() {
            val apple = valueOf("APPLE")
            println(apple.property1)
            apple.function1("BANANA")
        }
    }
}

interface SampleInterface {
    fun interfaceFunction(s: String)
}