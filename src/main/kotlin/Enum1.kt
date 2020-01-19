enum class Enum1(val value: Int) : Interface1 {
    Constant1(0) {
        override val field1: String
            get() = "foo"

        override fun function1(s: String) = println(s)
        override fun interfaceFunction1(s: String) = println(s)
    },
    Constant2(1) {
        override val field1: String
            get() = "bar"

        override fun function1(s: String) = println(s)
        override fun interfaceFunction1(s: String) = println(s)
    };

    abstract val field1: String
    abstract fun function1(s: String)

    companion object {
        fun create(value: Int) = values().first { it.value == value }
    }
}

interface Interface1 {
    fun interfaceFunction1(s: String)
}