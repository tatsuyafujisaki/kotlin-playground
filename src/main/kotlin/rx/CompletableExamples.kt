package rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

object CompletableExamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun example1() {
        val completable = Completable.complete()
        compositeDisposable.add(completable.mySubscribe())
        compositeDisposable.dispose()
    }

    fun example2() {
        val completable = Completable.error(Throwable("WTF"))
        compositeDisposable.add(completable.mySubscribe())
        compositeDisposable.dispose()
    }

    private fun Completable.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("CompletableObserver[$observerCount].onComplete") },
            { println("CompletableObserver[$observerCount].onError: $it") }
        )
    }
}
