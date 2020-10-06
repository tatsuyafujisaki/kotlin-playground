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
                // Called whenever a new subscriber is added.
                println("doOnSuccess: $it")
            }
            .doOnError {
                // Called whenever a new subscriber is added.
                it.printStackTrace()
            }

        single
            .subscribe(
                { println("onSuccess: $it") },
                { it.printStackTrace() },
            )

        single
            .subscribe(
                { println("onSuccess: $it") },
                { it.printStackTrace() },
            )
    }

    fun example2(single: Single<*>) {
        with(single) {
            subscribe { value ->
                println("onSuccess: $value")
            }
            subscribe(
                { println("onSuccess: $it") },
                { it.printStackTrace() },
            )
        }
    }
}