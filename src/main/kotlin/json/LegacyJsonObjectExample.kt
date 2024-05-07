package json

import org.json.JSONObject

private fun main() {
    val jsonObject = JSONObject(
            """
{
    "name": "Christopher Robin",
    "age": 6,
    "fictional": true,
    "friends": ["Pooh", "Piglet"],
    "favorite": {"novelist": "A. A. Milne"}
}
    """.trimIndent(),
    )
    println(jsonObject)
}