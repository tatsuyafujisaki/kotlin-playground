package flow

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

private suspend fun main() = coroutineScope {
    val myClass = MyClass()

    launch {
        flowFrom(myClass).collect {
            println("collect: $it")
        }
    }

    myClass.start()
}

private fun flowFrom(myClass: MyClass) = callbackFlow {
    val myCallback = object : MyCallback {
        override fun onChange(x: Long) {
            trySend(x)
        }
    }
    myClass.myCallback = myCallback

    awaitClose {}
}

private interface MyCallback {
    fun onChange(x: Long)
}

private class MyClass {
    var myCallback: MyCallback? = null

    suspend fun start() {
        generateSequence(0L, Long::inc).forEach {
            myCallback?.onChange(it)
            delay(1000)
        }
    }
}
