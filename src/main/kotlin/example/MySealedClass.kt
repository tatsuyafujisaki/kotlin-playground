package example

sealed class MySealedClass(
        private val myPrivatePredefinedProperty: String,
        val myPublicPredefinedProperty: String,
) {
    abstract val myAbstractProperty: String
}

data class MyDataClass1(
        override val myAbstractProperty: String,
        val property1: Int,
) : MySealedClass(
        myPrivatePredefinedProperty = "Predefined private value for MyDataClass1",
        myPublicPredefinedProperty = "Predefined public value for MyDataClass1",
)

data class MyDataClass2(
        override val myAbstractProperty: String,
        val property2: Int,
) : MySealedClass(
        myPrivatePredefinedProperty = "Predefined private value for MyDataClass2",
        myPublicPredefinedProperty = "Predefined public value for MyDataClass2",
)

fun main() {
}
