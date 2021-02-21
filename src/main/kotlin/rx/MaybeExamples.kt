package rx

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

object MaybeExamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun example() {
        val maybe = Maybe.just("a")

        compositeDisposable.add(maybe.mySubscribe())
        compositeDisposable.add(maybe.mySubscribe())

        compositeDisposable.clear()
    }

    private fun Maybe<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("MaybeObserver[$observerCount].onNext: $it") },
            { println("MaybeObserver[$observerCount].onError: $it") },
            { println("MaybeObserver[$observerCount].onComplete") }
        )
    }
}
