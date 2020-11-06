package rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable

object CompletableExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example() {
        val completable = Completable.create { emitter ->
            runCatching {
                // Do something.
            }.onSuccess {
                emitter.onComplete()
            }.onFailure {
                emitter.onError(it)
            }
        }

        with(completable) {
            mySubscribe(compositeDisposable)
            mySubscribe(compositeDisposable)
        }

        compositeDisposable.clear()
    }

    private fun Completable.mySubscribe(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onComplete") },
            { it.printStackTrace() }
        ).let(compositeDisposable::add)
    }
}