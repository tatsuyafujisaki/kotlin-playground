package util

fun <T> T.applyIf(condition: Boolean, block: T.() -> T) = if (condition) block() else this
fun <T> T.letIf(condition: Boolean, block: (T) -> T) = if (condition) block(this) else this
