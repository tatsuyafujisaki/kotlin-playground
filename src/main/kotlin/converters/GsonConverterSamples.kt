package converters

object GsonConverterSamples {
    fun sample() {
        val person = Person("Jane", 18)
        println(person)

        val json = GsonConverter.encodeToString(person)
        println(json)

        // Note that the returned value is non-nullable unlike Moshi.
        val person2: Person = GsonConverter.decodeFromString(json)
        println(person2)
    }
}
