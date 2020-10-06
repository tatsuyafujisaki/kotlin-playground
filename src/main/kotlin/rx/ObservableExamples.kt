package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

object ObservableExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example1() {
        val observable =
            Observable
                .just("apple", "orange")
                .doOnSubscribe {
                    // Called whenever a new subscriber is added.
                    println("doOnSubscribe")
                }
                .doOnNext {
                    // Called on each successful item whenever a new subscriber is added.
                    println("doOnNext: $it")
                }
                .doOnError {
                    // Called on error whenever a new subscriber is added.
                    it.printStackTrace()
                }
                .doOnComplete {
                    // Called whenever a new subscriber is added.
                    println("doOnComplete")
                }

        with(observable) {
            mySubscribe3(compositeDisposable)
            mySubscribe3(compositeDisposable)
        }

        compositeDisposable.dispose()
    }

    private fun Observable<*>.mySubscribe1(compositeDisposable: CompositeDisposable) {
        subscribe {
            println("onNext: $it")
        }.let(compositeDisposable::add)
    }

    private fun Observable<*>.mySubscribe2(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() }
        ).let(compositeDisposable::add)
    }

    private fun Observable<*>.mySubscribe3(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        ).let(compositeDisposable::add)
    }
}