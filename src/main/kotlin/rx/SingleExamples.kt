package rx

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * There is no such thing as Single#doOnNext(...) and Single#doOnComplete(...)
 * Moreover, Single#subscribe() does not take onNext(...) and onComplete(...).
 */
object SingleExamples {
    private val compositeDisposable = CompositeDisposable()

    fun example(item: String = "apple") {
        val single = Single
            .just(item)
            // .map { throw Throwable("wtf") }
            .doOnSuccess {
                // Called on success whenever a new subscriber is added.
                println("doOnSuccess: $it")
            }
            .doOnError {
                // Called on error whenever a new subscriber is added.
                println("doOnError: ${it.message}")
                it.printStackTrace()
            }
            .onErrorResumeNext {
                // Called on error whenever a new subscriber is added.
                println("onErrorResumeNext: ${it.message}")
                it.printStackTrace()
                Single.never()
            }

        with(single) {
            mySubscribe1(compositeDisposable)
            mySubscribe2(compositeDisposable)
        }

        compositeDisposable.dispose()
    }

    private fun Single<*>.mySubscribe1(compositeDisposable: CompositeDisposable) {
        subscribe { item ->
            println("onSuccess: $item")
        }.let(compositeDisposable::add)
    }

    private fun Single<*>.mySubscribe2(compositeDisposable: CompositeDisposable) {
        subscribe(
            { println("onSuccess: $it") },
            { it.printStackTrace() },
        ).let(compositeDisposable::add)
    }
}