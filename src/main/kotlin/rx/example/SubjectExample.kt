package rx.example

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.Subject
import util.RxUtil.DoOn.doOnMisc
import util.RxUtil.DoOn.print
import util.RxUtil.SubscribeUtil.mySubscribe

object SubjectExample {
    /**
     * It shows the difference among several types of subjects.
     * AsyncSubject emits the last value just after onComplete().
     * PublishSubject ignores past emissions before the subscription.
     * BehaviorSubject takes the last emission before the subscription.
     * ReplySubject ignores all the past emissions before the subscription.
     */
    fun example1() {
        fun demo(subject: Subject<String>) {
            val observable = subject.hide()
            subject.onNext("a")
            subject.onNext("b")
            observable.mySubscribe()
            subject.onNext("c")
            subject.onNext("d")
            subject.onComplete() // Without onComplete(), AsyncSubject will never emit a value.
        }
        println("-- AsyncSubject --")
        demo(AsyncSubject.create())
        println("-- PublishSubject --")
        demo(PublishSubject.create())
        println("-- BehaviorSubject --")
        demo(BehaviorSubject.create())
        println("-- ReplySubject --")
        demo(ReplaySubject.create())
    }

    /**
     * Example of calling Observer.onError()
     */
    fun example2() {
        val subject = PublishSubject.create<String>()
        subject
            .hide()
            .flatMap {
                Observable.error<String>(Throwable("WTF"))
            }
            .mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
    }

    /**
     * Example of Observable.onErrorResumeNext(), which calls onComplete() instead of onError().
     */
    fun example3() {
        val subject = PublishSubject.create<String>()
        subject
            .hide()
            .flatMap {
                Observable.error<String>(Throwable("WTF"))
            }
            .onErrorResumeNext {
                println("onErrorResumeNext (Immediately afterwards, Observer.onNext() and Observer.onComplete() will be called instead of Observer.onError(): $it")
                Observable.just("phoenix")
            }
            .mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
    }

    /**
     * This example shows that when [Observable.onErrorResumeNext] is inside [Observable.flatMap], the outer [Observable] continues as if no error occurred.
     */
    fun example4() {
        val subject = PublishSubject.create<String>()
        subject
            .hide()
            .flatMap {
                Observable
                    .error<String>(Throwable("WTF"))
                    .onErrorResumeNext {
                        println("onErrorResumeNext (Because this onErrorResumeNext is inside flatMap, the outer Observable will continue as if no error occurred.)")
                        Observable.just("phoenix")
                    }
            }
            .mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
    }

    /**
     * This example shows that even after a subscription's [Disposable] is disposed (e.g. by onError()),
     * you can subscribe to the same [Observable] and it works as if no error occurred before.
     */
    fun example5() {
        val subject = PublishSubject.create<String>()
        val observable = subject.hide()
        val disposable = observable
            .flatMap {
                Observable.error<String>(Throwable("WTF"))
            }
            .onErrorResumeNext {
                println("onErrorResumeNext (Immediately afterwards, Observer.onNext() and Observer.onComplete() will be called instead of Observer.onError(): $it")
                Observable.just("phoenix")
            }
            .mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
        disposable.print() // true
        val disposable2 = observable.mySubscribe()
        subject.onNext("c")
        subject.onNext("d")
        disposable2.print() // false
        subject.onComplete()
        disposable2.print() // true
    }

    /** Example of converting Observable to Completable */
    fun example6() {
        val subject = PublishSubject.create<Unit>()
        val completable = Completable.fromObservable(subject.hide())
        val disposable = completable.mySubscribe()
        subject.onComplete()
        disposable.print() // true
    }

    /** Example of converting Observable to Maybe */
    fun example7() {
        val subject = PublishSubject.create<String>()
        val maybe = Maybe.fromObservable(subject.hide())
        val disposable = maybe.mySubscribe()
        subject.onNext("a")
        subject.onComplete()
        disposable.print() // true
    }

    /** Example of converting Observable to Single */
    fun example8() {
        val subject = PublishSubject.create<String>()
        val single =
            Single.fromObservable(subject.hide()) // Unlike Observable.single(), you don't have to set the default value.
        val disposable = single.mySubscribe()
        subject.onNext("a")
        subject.onComplete()
        disposable.print() // true
    }

    /** Example of Observable.retry() */
    fun example9() {
        var firstRun = true
        val subject = PublishSubject.create<String>()
        val observable = subject
            .hide()
            .doOnMisc()
            .flatMap {
                if (firstRun) {
                    firstRun = false
                    Observable.error(Throwable("WTF"))
                } else {
                    Observable.just(it)
                }
            }
            .retry()
        val disposable = observable.mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
        subject.onComplete()
        disposable.print() // true
    }

    /** demonstrates that Subject multicasts. */
    fun example10() {
        val subject = PublishSubject.create<String>()
        val observable = subject.hide()

        observable.mySubscribe()
        observable.mySubscribe()

        subject.onNext("a")
        subject.onNext("b")
    }

    /** demonstrates that ObservableEmitter uni-casts. */
    fun example11() {
        lateinit var emitter: ObservableEmitter<String>
        val observable = Observable.create<String> {
            emitter = it
        }

        observable.mySubscribe()
        observable.mySubscribe()

        emitter.onNext("a")
        emitter.onNext("b")
    }
}
