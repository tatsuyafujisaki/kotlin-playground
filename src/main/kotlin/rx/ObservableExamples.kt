package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable

object ObservableExamples {
    private val compositeDisposable = CompositeDisposable()

    val observable1: Observable<Any> = Observable.empty()
    val observable2: Observable<Any> = Observable.never()
    val observable3: Observable<Any> = Observable.error(Throwable())

    fun example1(items: List<String> = listOf("apple", "orange")) {
        val observable =
            Observable
                .fromIterable(items)
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
            mySubscribe(compositeDisposable)
            mySubscribe(compositeDisposable)
        }

        compositeDisposable.dispose()
    }

    fun example2() {
        var emitter: ObservableEmitter<String>? = null
        val observable = Observable.create<String> { emitter = it }

        with(observable) {
            emitter?.onNext("apple") // has no effect because the observable is not subscribed to yet.
            mySubscribe(compositeDisposable)
            emitter?.onNext("orange")
            emitter?.onComplete()
        }

        compositeDisposable.dispose()
    }

    /**
     * Observable unicasts by design but multicasts by calling share().
     *
     * Output:
     * Subscriber1: apple
     * Subscriber1: orange <- shown only if Observable#share() is used.
     * Subscriber2: orange
     */
    fun example3() {
        var emitter: ObservableEmitter<String>? = null
        val observable = Observable.create<String> { emitter = it }

        with(observable) {
            subscribe {
                println("Subscriber1: $it")
            }.let(compositeDisposable::add)

            emitter?.onNext("apple")

            subscribe {
                println("Subscriber2: $it")
            }.let(compositeDisposable::add)

            emitter?.onNext("orange")
        }

        compositeDisposable.dispose()
    }

    private fun Observable<*>.mySubscribe(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        ).let(compositeDisposable::add)
    }
}