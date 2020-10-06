package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

object SubjectExamples {
    var firstExecution = true

    fun example1() {
        val subject = PublishSubject.create<Unit>()
        val observable = subject.hide()

        observable
            .map {
                if (firstExecution) {
                    firstExecution = false
                } else {
                    throw Throwable("wtf")
                }
            }
            .onErrorResumeNext {
                println("onErrorResumeNext")
                Observable.empty()
            }
            .subscribe {
                println("subscribe")
            }

        subject.onNext(Unit) // prints "subscribe".
        subject.onNext(Unit) // prints "onErrorResumeNext".
        subject.onNext(Unit) // does nothing.
    }

    fun example2() {
        val subject = PublishSubject.create<Unit>()
        subject.subscribe {
            println("subscribe")
        }
        subject.onNext(Unit) // prints "subscribe".
        subject.onNext(Unit) // prints "subscribe".
    }

    fun example3() {
        val subject: PublishSubject<Unit> = PublishSubject.create()

        val observable1: Observable<Unit> = subject
        val subject2: Subject<Unit>? = observable1 as? Subject // PublishSubject

        val observable2: Observable<Unit> = subject.hide()
        val subject3: Subject<Unit>? = observable2 as? Subject // null because of hide()

        println(subject2)
        println(subject3)
    }
}