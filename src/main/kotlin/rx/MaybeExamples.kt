package rx

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable

object MaybeExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example() {
        val maybe = Maybe.just("apple")
        val single = maybe.defaultIfEmpty("orange")

        maybe.mySubscribe(compositeDisposable)
        maybe.mySubscribe(compositeDisposable)

        compositeDisposable.clear()
    }

    private fun Maybe<*>.mySubscribe(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        ).let(compositeDisposable::add)
    }
}