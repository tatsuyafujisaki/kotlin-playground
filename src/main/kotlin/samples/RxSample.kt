package samples

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

/**
 * Output:
 * b
 * c
 * done
 */
fun rxSample() {
    listOf("a", "b", "c", "d", "e")
            .toObservable()
            .filter { it == "b" || it == "c" }
            .subscribeBy(
                    onNext = { println(it) },
                    onError = { it.printStackTrace() },
                    onComplete = { println("done") }
            )
}