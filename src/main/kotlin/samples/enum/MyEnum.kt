package samples.enum

interface MyInterface {
    fun interfaceFunction(s: String)
}

enum class MyEnum(val value: Int) : MyInterface {
    FOO(100) {
        override val myField: String
            get() = "I am foo."

        override fun interfaceFunction(s: String) {
            println("Foo does somethign with $s.")
        }

        override fun doSomething(s: String) {
            println("Foo does something with $s.")
        }
    },
    BAR(200) {
        override val myField: String
            get() = "I am bar."

        override fun interfaceFunction(s: String) {
            println("Bar does something with $s.")
        }

        override fun doSomething(s: String) {
            println("Bar does something with $s.")
        }
    };

    abstract val myField: String
    abstract fun doSomething(s: String)

    companion object {
        fun example() {
            val foo: MyEnum = valueOf("FOO")
            println(foo.myField)
            foo.doSomething("XYZ")
        }
    }
}