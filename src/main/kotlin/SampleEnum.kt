enum class SampleEnum(val value: Int) : SampleInterface {
    ConstantSample(0) {
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

interface SampleInterface {
    fun interfaceFunction1(s: String)
}