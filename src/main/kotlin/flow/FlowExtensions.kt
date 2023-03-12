package flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @see <a href="https://github.com/Kotlin/kotlinx.coroutines/issues/1446#issuecomment-525565729">throttleFirst</a>
 */
fun <T> Flow<T>.throttleFirst(windowDuration: Long, getCurrentTimeMillis: (() -> Long)? = null): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect {
        val currentTime = getCurrentTimeMillis?.invoke() ?: System.currentTimeMillis()
        if (windowDuration < (currentTime - lastEmissionTime)) {
            lastEmissionTime = currentTime
            emit(it)
        }
    }
}
