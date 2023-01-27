package flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry

private suspend fun main() = coroutineScope {
    var retry = 0
    val maxRetryCount = 3L

    flow {
        if (retry < maxRetryCount) {
            retry++
            throw Exception()
        }
        emit("a")
    }.retry(maxRetryCount) {
        println("retry! $it")
        true
    }.catch {
        println("catch! $it")
    }.collect {
        println("collect! $it")
    }
}