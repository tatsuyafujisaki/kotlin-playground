package converters

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiConverter {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    inline fun <reified T> toJson(value: T): String = moshi.adapter(T::class.java).toJson(value)
    inline fun <reified T> fromJson(string: String) = moshi.adapter(T::class.java).fromJson(string)
}
