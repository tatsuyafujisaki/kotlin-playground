package json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/** @sample MoshiConverterSamples */
object MoshiConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> encodeToString(value: T): String = moshi.adapter(T::class.java).toJson(value)
    inline fun <reified T> decodeFromString(string: String) = moshi.adapter(T::class.java).fromJson(string)
}
