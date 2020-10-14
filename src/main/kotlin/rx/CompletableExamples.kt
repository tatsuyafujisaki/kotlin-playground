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
            mySubscribe1(compositeDisposable)
            mySubscribe2(compositeDisposable)
        }

        compositeDisposable.dispose()
    }

    private fun Completable.mySubscribe1(compositeDisposable: CompositeDisposable) {
        subscribe {
            println("onComplete")
        }.let(compositeDisposable::add)
    }

    private fun Completable.mySubscribe2(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onComplete") },
            { it.printStackTrace() }
        ).let(compositeDisposable::add)
    }
}