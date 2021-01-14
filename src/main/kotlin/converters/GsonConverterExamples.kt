package converters

object GsonConverterExamples {
    fun example() {
        val person1 = Person("Jane", 18)
        val json: String = GsonConverter.toJson(person1)
        println(json)

        val person2 = GsonConverter.fromJson<Person>(json)
        println(person2)
    }
}
