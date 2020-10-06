package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

object ObservableExamples {
    private val compositeDisposable = CompositeDisposable()

    val observable1: Observable<Any> = Observable.empty()
    val observable2: Observable<Any> = Observable.never()
    val observable3: Observable<Any> = Observable.error(Throwable())

    fun example1(items: List<String> = listOf("apple", "orange")) {
        val observable =
            Observable
                .fromIterable(items.asIterable())
                // .map { throw Throwable("wtf") }
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
                    println("doOnError: ${it.message}")
                    it.printStackTrace()
                }
                .onErrorResumeNext {
                    // Called on error whenever a new subscriber is added.
                    println("onErrorResumeNext: ${it.message}")
                    it.printStackTrace()
                    Observable.never()
                }
                .doOnComplete {
                    // Called on complete with no error whenever a new subscriber is added.
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