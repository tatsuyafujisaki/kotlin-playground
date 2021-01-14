package converters

object MoshiConverterExamples {
    fun example() {
        val person = Person("Jane", 18)
        println(person)

        val json = MoshiConverter.toJson(person)
        println(json)

        // Note that the returned value is nullable unlike Gson.
        val person2: Person? = MoshiConverter.fromJson(json)
        println(person2)
    }
}
