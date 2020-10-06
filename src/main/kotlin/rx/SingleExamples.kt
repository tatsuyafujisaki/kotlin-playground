package rx

import io.reactivex.rxjava3.core.Single

/**
 * There is no such thing as Single#doOnNext(...) and Single#doOnComplete(...)
 * Moreover, Single#subscribe() does not take onNext(...) and onComplete(...).
 */
object SingleExamples {
    fun example1() {
        val single = Single
            .just("apple")
            .doOnSuccess {
                // Called on success whenever a new subscriber is added.
                println("doOnSuccess: $it")
            }
            .doOnError {
                // Called on error whenever a new subscriber is added.
                it.printStackTrace()
            }

        with(single) {
            mySubscribe1()
            mySubscribe2()
        }
    }

    private fun Single<*>.mySubscribe1() {
        subscribe { value ->
            println("onSuccess: $value")
        }
    }

    private fun Single<*>.mySubscribe2() {
        subscribe(
            { println("onSuccess: $it") },
            { it.printStackTrace() },
        )
    }
}