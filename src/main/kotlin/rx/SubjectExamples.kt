package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

object SubjectExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example() {
        val subject = PublishSubject.create<Unit>()
        val observable = subject.hide()
        var firstExecution = true

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
            }.let(compositeDisposable::add)

        subject.onNext(Unit) // prints "subscribe".
        subject.onNext(Unit) // prints "onErrorResumeNext".
        subject.onNext(Unit) // does nothing.

        compositeDisposable.dispose()
    }

    fun example2() {
        val subject = PublishSubject.create<Unit>()

        subject.subscribe({
            println("onNext")
        }, {
            println("onError")
        }, {
            println("onComplete")
        }).let(compositeDisposable::add)

        subject.onNext(Unit) // prints "onNext".
        subject.onNext(Unit) // prints "onNext".
        subject.onComplete() // prints "Complete".

        compositeDisposable.dispose()
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