package example

sealed class MySealedClass(
        private val myPrivatePredefinedProperty: String,
        val myPublicPredefinedProperty: String,
) {
    abstract val myDynamicallyDefinedProperty: String
}

private data class MyDataClass3(
        override val myDynamicallyDefinedProperty: String,
        val property1: String,
) : MySealedClass(
        myPrivatePredefinedProperty = "Predefined private value for MyDataClass3",
        myPublicPredefinedProperty = "Predefined public value for MyDataClass3",
)

private data class MyDataClass4(
        override val myDynamicallyDefinedProperty: String,
        val property2: String,
) : MySealedClass(
        myPrivatePredefinedProperty = "Predefined private value for MyDataClass4",
        myPublicPredefinedProperty = "Predefined public value for MyDataClass4",
)

fun main() {
    val myDataClass1 = MyDataClass3(
            myDynamicallyDefinedProperty = "Dynamically defined value for MyDataClass3",
            property1 = "Property1!",
    )

    val myDataClass2 = MyDataClass4(
            myDynamicallyDefinedProperty = "Dynamically defined value for MyDataClass4",
            property2 = "Property2!",
    )
}
