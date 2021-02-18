package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.Subject
import rx.ObservableExamples.mySubscribe

object SubjectExamples {
    /**
     * Shows the difference among PublishSubject, BehaviorSubject, and ReplySubject
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

    fun example2() {
        val subject = PublishSubject.create<String>()
        val observable = subject.share().hide()
        observable.mySubscribe()
        subject.onNext("a")
        observable.mySubscribe()
        subject.onNext("b")
        subject.onComplete()
    }

    fun example3() {
        val subject = PublishSubject.create<String>()
        val observable = subject.share().hide()
        observable.mySubscribe()
        observable.onErrorResumeNext {
            println("onErrorResumeNext: $it")
            return@onErrorResumeNext Observable.just("")
        }
        subject.onNext("a")
        observable.mySubscribe()
        subject.onNext("b")
    }
}
