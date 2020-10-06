package rx

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable

object MaybeExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example1() {
        val maybe = Maybe.just("apple")
        val single = maybe.defaultIfEmpty("orange")

        maybe.mySubscribe3(compositeDisposable)
        maybe.mySubscribe3(compositeDisposable)
        
        compositeDisposable.dispose()
    }

    private fun Maybe<*>.mySubscribe1(compositeDisposable: CompositeDisposable) {
        subscribe {
            println("onNext: $it")
        }.let(compositeDisposable::add)
    }

    private fun Maybe<*>.mySubscribe2(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() }
        ).let(compositeDisposable::add)
    }

    private fun Maybe<*>.mySubscribe3(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        ).let(compositeDisposable::add)
    }
}