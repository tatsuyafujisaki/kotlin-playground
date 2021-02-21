package rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import rx.DisposeUtil.print

object CompletableExamples {
    private var observerCount = 0

    fun example1() {
        val completable = Completable.complete()
        val disposable = completable.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val completable = Completable.error(Throwable("WTF"))
        val disposable = completable.mySubscribe()
        disposable.print() // true
    }

    private fun Completable.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("CompletableObserver[$observerCount].onComplete") },
            { println("CompletableObserver[$observerCount].onError: $it") }
        )
    }
}
