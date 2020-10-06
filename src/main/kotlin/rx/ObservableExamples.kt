package rx

import io.reactivex.rxjava3.core.Observable

object ObservableExamples {
    fun example1() {
        val observable =
            Observable
                .just("apple", "orange", "banana")
                .doOnSubscribe {
                    // Called whenever a new subscriber is added.
                    println("doOnSubscribe")
                }
                .doOnNext {
                    // Called on each successful item whenever a new subscriber is added.
                    println("doOnNext: $it")
                }
                .doOnError {
                    // Called on error whenever a new subscriber is added.
                    it.printStackTrace()
                }
                .doOnComplete {
                    // Called whenever a new subscriber is added.
                    println("doOnComplete")
                }

        with(observable) {
            mySubscribe3()
            mySubscribe3()
        }
    }

    private fun Observable<*>.mySubscribe1() {
        subscribe {
            println("onNext: $it")
        }
    }

    private fun Observable<*>.mySubscribe2() {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() }
        )
    }

    private fun Observable<*>.mySubscribe3() {
        subscribe(
            { println("onNext: $it") },
            { it.printStackTrace() },
            { println("onComplete") }
        )
    }
}