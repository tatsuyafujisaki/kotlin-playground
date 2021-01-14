package converters

object MoshiConverterExamples {
    fun example() {
        val person = Person("Jane", 18)
        val json: String = MoshiConverter.toJson(person)
        println(json)

        val person2: Person? = MoshiConverter.fromJson(json)
        println(person2)
    }
}
