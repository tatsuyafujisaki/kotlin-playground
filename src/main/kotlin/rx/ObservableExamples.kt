package rx

import io.reactivex.rxjava3.core.Observable

object ObservableExamples {
    fun example1() {
        val observable =
            Observable
                .just("apple", "orange")
                .doOnNext {
                    // Called whenever a new subscriber is added.
                    println("doOnNext: $it")
                }
                .doOnError {
                    // Called whenever a new subscriber is added.
                    it.printStackTrace()
                }
                .doOnComplete {
                    // Called whenever a new subscriber is added.
                    println("doOnComplete")
                }

        observable.subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        )

        observable.subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        )
    }

    fun example2(observable: Observable<*>) {
        with(observable) {
            subscribe {
                println("onNext: $it")
            }
            subscribe(
                { println("onNext: $it") },
                { it.printStackTrace() },
            )
            subscribe(
                { println("onNext: $it") },
                { it.printStackTrace() },
                { println("onComplete") }
            )
        }
    }
}