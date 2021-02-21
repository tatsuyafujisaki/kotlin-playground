package rx

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * There is no such thing as Single#doOnNext(...) and Single#doOnComplete(...)
 * Moreover, Single#subscribe() does not take onNext(...) and onComplete(...).
 */
object SingleExamples {
    private val compositeDisposable = CompositeDisposable()
    private var observerCount = 0

    fun example(item: String = "apple") {
        val single = Single
            .just(item)
            // .map { throw Throwable("WTF") }
            .doOnSuccess {
                println("doOnSuccess: $it")
            }
            .doOnError {
                println("doOnError: ${it.message}")
                it.printStackTrace()
            }
            .onErrorResumeNext {
                println("onErrorResumeNext: ${it.message}")
                it.printStackTrace()
                Single.never()
            }
        with(single) {
            compositeDisposable.add(mySubscribe())
            compositeDisposable.add(mySubscribe())
        }
        compositeDisposable.clear()
    }

    fun Single<*>.mySubscribe(): Disposable {
        observerCount++
        return subscribe(
            { println("SingleObserver[$observerCount].onSuccess: $it") },
            { println("SingleObserver[$observerCount].onError: $it") },
        )
    }
}
