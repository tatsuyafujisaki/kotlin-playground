package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

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
     * Observable unicasts by default. Observable can take only one Subscriber. Registering the second Subscriber unregisters the first Subscriber.
     * Observable multicasts after calling share().
     *
     * Observable.share() is an alias for publish().refCount().
     * Observable.publish() returns a ConnectableObservable, which starts emitting items, not when subscribed, but when connect() is called. By which, a ConnectableObservable can emit items only after all the subscribers subscribe.
     * Observable.refCount() returns an Observable that will be disposed when all the subscribers are unsubscribed.
     *
     * Output:
     * Observer(1st).onNext: apple
     * Observer(1nd).onNext: banana <- not shown because Observable can take only one Subscriber. Registering the second Subscriber unregisters the first Subscriber.
     * Observer(2nd).onNext: banana
     * Observer(3rd).onNext: grape
     * Observer(3rd).onNext: orange <- shown because share() is called.
     * Observer(4th).onNext: orange
     */
    fun example3() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        var emitter: ObservableEmitter<String>? = null
        with(Observable.create<String> { emitter = it }) {
            mySubscribe()
            emitter?.onNext("apple")
            mySubscribe()
            emitter?.onNext("banana")
        }
        // Note share().
        with(Observable.create<String> { emitter = it }.share()) {
            mySubscribe()
            emitter?.onNext("grape")
            mySubscribe()
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
                println("Observable.onErrorResumeNext (prevents onError() from being called.): $it")
                Observable.just("peach")
            }
            .mySubscribe(2)
        compositeDisposable.clear()
    }

    private fun Observable<String>.error() =
        map {
            if (it != "apple") {
                throw Throwable("WTF")
            }
            it
        }

    /**
     * error() and error2() don't make any difference.
     */
    private fun Observable<String>.error2() =
        flatMap {
            if (it != "apple") {
                Observable.error(Throwable("WTF"))
            } else {
                Observable.just(it)
            }
        }

    fun Observable<*>.mySubscribe(n: Int): List<Disposable> {
        val disposables = mutableListOf<Disposable>()
        for (i in 1..n) {
            disposables.add(subscribe(
                { println("Observer[$i].onNext: $it") },
                { println("Observer[$i].onError (Afterwards, Observer.onNext() and Observer.onComplete() will never be called.): $it") },
                { println("Observer[$i].onComplete (Afterwards, Observer.onNext() will never be called.)") }
            ))
        }
        return disposables
    }

    fun Observable<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("Observer[$observerCount].onNext: $it") },
            { println("Observer[$observerCount].onError (Afterwards, Observer.onNext() and Observer.onComplete() will never be called.): $it") },
            { println("Observer[$observerCount].onComplete (Afterwards, Observer.onNext() will never be called.)") }
        )
    }
}
