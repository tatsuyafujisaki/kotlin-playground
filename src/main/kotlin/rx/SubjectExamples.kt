package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

object SubjectExamples {
    fun example1() {
        val subject: PublishSubject<Unit> = PublishSubject.create()

        val observable1: Observable<Unit> = subject
        val subject2: Subject<Unit>? = observable1 as? Subject // PublishSubject

        val observable2: Observable<Unit> = subject.hide()
        val subject3: Subject<Unit>? = observable2 as? Subject // null because of hide()

        println(subject2)
        println(subject3)
    }
}