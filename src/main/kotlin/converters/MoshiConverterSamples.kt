package converters

object MoshiConverterSamples {
    fun sample() {
        val person = Person("Jane", 18)
        println(person)

        val json = MoshiConverter.encodeToString(person)
        println(json)

        // Note that the returned value is nullable unlike Kotlin Serialization and Gson.
        val person2: Person? = MoshiConverter.decodeFromString(json)
        println(person2)
    }
}
