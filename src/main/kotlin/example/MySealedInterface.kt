package example

sealed interface MySealedInterface {
    val myDynamicallyDefinedProperty: String
}

private data class MyDataClass1(
        override val myDynamicallyDefinedProperty: String,
        val property1: String,
) : MySealedInterface

private data class MyDataClass2(
        override val myDynamicallyDefinedProperty: String,
        val property2: String,
) : MySealedInterface

fun main() {
    val myDataClass1 = MyDataClass1(
            myDynamicallyDefinedProperty = "Dynamically defined value for MyDataClass1",
            property1 = "Property1!",
    )

    val myDataClass2 = MyDataClass2(
            myDynamicallyDefinedProperty = "Dynamically defined value for MyDataClass2",
            property2 = "Property2!",
    )
}
