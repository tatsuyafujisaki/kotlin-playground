package rx.kotlinobservable

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import kotlin.properties.Delegates

object KotlinObservableExample {
    private val mySubject: Subject<String> = PublishSubject.create()
    private val myObservable = mySubject.hide().distinctUntilChanged()

    var s by Delegates.observable("") { _, _, new ->
        mySubject.onNext(new)
    }
}

object KotlinObservableExample2 {
    private val mySubject: Subject<String> = PublishSubject.create()
    private val myObservable = mySubject.hide().distinctUntilChanged()

    var s = ""
        set(value) {
            field = value
            mySubject.onNext(value)
        }
}
