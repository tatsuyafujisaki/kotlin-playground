package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import util.IntUtil.nth

object ObservableExamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun example1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable().mySubscribe(2)
        compositeDisposable.clear()
    }

    fun example2() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        var emitter: ObservableEmitter<String>? = null
        with(Observable.create<String> { emitter = it }) {
            emitter?.onNext("apple") // has no effect because the observable is not subscribed to yet.
            mySubscribe()
            emitter?.onNext("orange")
            emitter?.onComplete()
        }
        compositeDisposable.clear()
    }

    /**
     * Observable unicasts by design but multicasts by calling share().
     *
     * Output:
     * Observer1: apple
     * Observer1: orange <- shown only if Observable#share() is used.
     * Observer1: orange
     */
    fun example3() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        var emitter: ObservableEmitter<String>? = null
        with(Observable.create<String> { emitter = it }) {
            myCountingSubscribe()
            emitter?.onNext("apple")
            myCountingSubscribe()
            emitter?.onNext("orange")
        }
        compositeDisposable.clear()
    }

    fun errorExample1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createErrorObservable<String>().mySubscribe(2)
        compositeDisposable.clear()
    }

    fun errorExample2() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable().error().mySubscribe(2)
        compositeDisposable.clear()
    }

    fun errorExample3() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable().error2().mySubscribe(2)
        compositeDisposable.clear()
    }

    fun errorExample4() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable()
            .error()
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext (Instead, onError() is not called.): $it")
                Observable.just("peach")
            }
            .mySubscribe(2)
        compositeDisposable.clear()
    }

    fun errorExample5() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable()
            .error()
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext: $it")
                println("Observable.empty (Immediately after this, Observer.onComplete() will be called without Observer.onNext(). Calling Observable.onComplete() means subsequent items will be discarded.)")
                Observable.empty()
            }
            .mySubscribe(2)
        compositeDisposable.clear()
    }

    fun errorExample6() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        ObservableFactory.createObservable()
            .error()
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext: $it")
                println("Observable.never (None of Observer's methods will be called for this item, but the effect is independent of subsequent items.)")
                Observable.never()
            }
            .mySubscribe(2)
        compositeDisposable.clear()
    }

    private fun Observable<String>.error() =
        map {
            if (it != "apple") {
                throw Throwable("wtf")
            }
            it
        }

    /**
     * error() and error2() don't make any difference.
     */
    private fun Observable<String>.error2() =
        flatMap {
            if (it != "apple") {
                Observable.error(Throwable("wtf"))
            } else {
                Observable.just(it)
            }
        }

    private fun Observable<*>.mySubscribe(n: Int = 1) {
        for (i in 1..n) {
            val nthObserver = i.nth()
            subscribe(
                { println("$nthObserver Observer.onNext: $it") },
                { println("$nthObserver Observer.onError (After this, Observer.onNext() and Observer.onComplete() will never be called.): $it") },
                { println("$nthObserver Observer.onComplete (After this, Observer.onNext() will never be called.)") }
            ).let(compositeDisposable::add)
        }
    }

    private fun Observable<*>.myCountingSubscribe() {
        observerCount += 1
        val nthObserver = observerCount.nth()
        subscribe(
            { println("$nthObserver Observer.onNext: $it") },
            { println("$nthObserver Observer.onError (After this, Observer.onNext() and Observer.onComplete() will never be called.): $it") },
            { println("$nthObserver Observer.onComplete (After this, Observer.onNext() will never be called.)") }
        ).let(compositeDisposable::add)
    }
}
