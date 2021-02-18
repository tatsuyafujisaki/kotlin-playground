package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.Subject
import rx.ObservableExamples.mySubscribe

object SubjectExamples {
    /**
     * It shows the difference among PublishSubject, BehaviorSubject, and ReplySubject
     * PublishSubject ignores past emissions before the subscription.
     * BehaviorSubject takes the latest emission before the subscription.
     * ReplySubject ignores all the past emissions before the subscription.
     */
    fun example1() {
        fun demo(subject: Subject<String>) {
            val observable = subject.share().hide()
            subject.onNext("a")
            subject.onNext("b")
            observable.mySubscribe()
            subject.onNext("c")
            subject.onComplete()
        }

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
        val observable = subject.share().hide()
        observable
            // One way to throw an error.
            .map {
                throw Throwable("wtf1")
            }
            // Another way to throw an error.
            .flatMap {
                Observable.error<String>(Throwable("wtf2"))
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
        val observable = subject.share().hide()
        observable
            .flatMap {
                Observable.error<String>(Throwable("wtf"))
            }
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext (Immediately afterwards, Observer.onNext() and Observer.onComplete() will be called instead of Observer.onError(): $it")
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
        val observable = subject.share().hide()
        val disposable = observable
            .flatMap {
                Observable
                    .error<String>(Throwable("wtf"))
                    .onErrorResumeNext {
                        println("Observable.onErrorResumeNext (Because this onErrorResumeNext is inside flatMap, the outer Observable will continue as if no error occurred.)")
                        Observable.just("phoenix")
                    }
            }
            .mySubscribe()
        subject.onNext("a")
        subject.onNext("b")
        disposable.dispose()
    }

    /**
     * This example shows that even after a subscription's [Disposable] is disposed (e.g. by onError()),
     * you can subscribe to the same [Observable] and it works as if no error occurred before.
     */
    fun example5() {
        val subject = PublishSubject.create<String>()
        val observable = subject.share().hide()
        val disposable = observable
            .flatMap {
                Observable.error<String>(Throwable("wtf"))
            }
            .onErrorResumeNext {
                println("Observable.onErrorResumeNext (Immediately afterwards, Observer.onNext() and Observer.onComplete() will be called instead of Observer.onError(): $it")
                Observable.just("phoenix")
            }
            .mySubscribe()
        println(disposable.isDisposed) // true
        subject.onNext("a")
        subject.onNext("b")
        observable.mySubscribe()
        subject.onNext("c")
        subject.onNext("d")
        println(disposable.isDisposed) // false
        subject.onComplete()
        println(disposable.isDisposed) // true
    }
}
