package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import rx.RxJavaIngredients.doOnMisc

object ObservableExamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun example1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        RxJavaIngredients.createObservable().mySubscribe()
        RxJavaIngredients.createObservable().mySubscribe()
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

    /**
     * Example of Observable.empty()
     */
    fun observableEmptyExample() {
        val disposable = Observable
            .just("a", "b", "c", "d")
            .flatMap {
                if (it == "b") Observable.empty() else Observable.just(it)
            }
            .doOnMisc()
            .mySubscribe()
        println(disposable.isDisposed)
    }

    fun errorExample1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        RxJavaIngredients.createObservable().error().mySubscribe()
        RxJavaIngredients.createObservable().error().mySubscribe()
        compositeDisposable.clear()
    }

    fun errorExample2() {
        RxJavaIngredients.createObservable().error2().mySubscribe()
        RxJavaIngredients.createObservable().error2().mySubscribe()
        compositeDisposable.clear()
    }

    fun errorExample3() {
        val observable = RxJavaIngredients.createObservable()
            .error()
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext (prevents onError() from being called.): $it")
                Observable.just("peach")
            }
        observable.mySubscribe()
        observable.mySubscribe()
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

    fun Observable<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("Observer[$observerCount].onNext: $it") },
            { println("Observer[$observerCount].onError (Afterwards, Observer.onNext() and Observer.onComplete() will never be called.): $it") },
            { println("Observer[$observerCount].onComplete (Afterwards, Observer.onNext() will never be called.)") }
        )
    }
}
