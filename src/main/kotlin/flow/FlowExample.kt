package flow
import java.time.LocalTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun infiniteFlow() = flow {
    while(true) {
        emit(LocalTime.now())
        delay(1000)
    }
}

private fun main(): Unit = runBlocking {
    launch {
        infiniteFlow().take(5).collect {
            println(it)
        }
    }
}
