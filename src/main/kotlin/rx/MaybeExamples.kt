package rx

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable
import rx.DisposeUtil.print

object MaybeExamples {
    private var observerCount = 0

    fun example1() {
        val maybe = Maybe.just("a")
        val disposable = maybe.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val maybe = Maybe.error<Unit>(Throwable("WTF"))
        val disposable = maybe.mySubscribe()
        disposable.print() // true
    }

    fun Maybe<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("MaybeObserver[$observerCount].onNext: $it") },
            { println("MaybeObserver[$observerCount].onError: $it") },
            { println("MaybeObserver[$observerCount].onComplete") }
        )
    }
}
