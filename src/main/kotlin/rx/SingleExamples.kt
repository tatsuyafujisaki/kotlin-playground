package rx

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import rx.DisposeUtil.print

object SingleExamples {
    private var observerCount = 0

    fun example1() {
        val single = Single.just("a")
        val disposable = single.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val single = Single.error<Unit>(Throwable("WTF"))
        val disposable = single.mySubscribe()
        disposable.print() // true
    }

    private fun Single<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("SingleObserver[$observerCount].onSuccess: $it") },
            { println("SingleObserver[$observerCount].onError: $it") },
        )
    }
}
