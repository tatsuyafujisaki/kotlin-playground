package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import util.RxJavaUtil.doOnMisc
import util.RxJavaUtil.errorWhen
import util.RxJavaUtil.errorWhen2
import util.RxJavaUtil.mySubscribe
import util.RxJavaUtil.mySubscribeWithId

object ObservableSamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun <T> Observable<T>.mySubscribeWithId() {
        return mySubscribeWithId(observerCount++)
    }

    fun example1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        Observable.just("a", "b", "c").mySubscribe()
        compositeDisposable.clear()
    }

    fun example2() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        var emitter: ObservableEmitter<String>? = null
        val observable = Observable.create<String> {
            emitter = it
        }
        emitter?.onNext("a") // has no effect because the observable is not subscribed to yet.
        observable.mySubscribe()
        emitter?.onNext("b")
        emitter?.onComplete()
    }

    /**
     * Observable unicasts by default. Observable can take only one Subscriber. Registering the second Subscriber unregisters the first Subscriber.
     * Observable multicasts after calling share().
     *
     * [Observable.share] is an alias for publish().refCount().
     * [Observable.publish] returns a ConnectableObservable, which starts emitting items, not when subscribed, but when connect() is called. By which, a ConnectableObservable can emit items only after all the subscribers subscribe.
     * [ConnectableObservable.refCount] returns an Observable that will be disposed when all the subscribers are unsubscribed.
     *
     * Output:
     * Observer[0]#onNext: a
     * Observer[1]#onNext: b <- not shown because Observable can only register the last subscriber. Registering the second subscriber unregisters the first subscriber.
     * Observer[1]#onNext: b
     * Observer[2]#onNext: c
     * Observer[2]#onNext: d <- shown because share() is called.
     * Observer[3]#onNext: d
     */
    fun example3() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        var emitter: ObservableEmitter<String>? = null
        val observable = Observable.create<String> {
            emitter = it
        }

        observable.mySubscribeWithId()
        emitter?.onNext("a")
        observable.mySubscribeWithId()
        emitter?.onNext("b")

        // Note share().
        val observable2 = Observable.create<String> {
            emitter = it
        }.share()
        observable2.mySubscribeWithId()
        emitter?.onNext("c")
        observable2.mySubscribeWithId()
        emitter?.onNext("d")
    }

    /**
     * Example of Observable.empty()
     */
    fun observableEmptyExample() {
        val disposable = Observable
            .just("a", "b", "c")
            .flatMap {
                if (it == "b") Observable.empty() else Observable.just(it)
            }
            .doOnMisc()
            .mySubscribe()
        println(disposable.isDisposed)
    }

    fun errorExample1() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        Observable
            .just("a", "b", "c")
            .errorWhen("b")
            .mySubscribe()
        compositeDisposable.clear()
    }

    fun errorExample2() {
        println("-- " + object {}.javaClass.enclosingMethod?.name + " --")
        Observable.just("a", "b", "c")
            .errorWhen2("b")
            .mySubscribe()
        compositeDisposable.clear()
    }

    fun errorExample3() {
        val observable =
            Observable.just("a", "b", "c")
                .errorWhen("b")
                .onErrorResumeNext {
                    println("Observable#onErrorResumeNext: $it")
                    Observable.just("z")
                }
        observable.mySubscribe()
        observable.mySubscribe()
        compositeDisposable.clear()
    }
}
