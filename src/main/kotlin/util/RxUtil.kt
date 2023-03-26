package util

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import util.RxUtil.DoOn.doOnMisc

object RxUtil {
    object DoOn {
        fun Completable.doOnMisc(): Completable = doOnSubscribe {
            println("👀doOnSubscribe")
        }.doOnComplete {
            println("👀doOnComplete")
        }.doOnError {
            println("👀doOnError: $it")
        }.doOnTerminate {
            println("👀doOnTerminate")
        }.doOnDispose {
            println("👀doOnDispose")
        }

        fun <T : Any> Single<T>.doOnMisc(): Single<T> = doOnSubscribe {
            println("👀doOnSubscribe")
        }.doOnSuccess {
            println("👀doOnSuccess: $it")
        }.doOnError {
            println("👀doOnError: $it")
        }.doOnTerminate {
            println("👀doOnTerminate")
        }.doOnDispose {
            println("👀doOnDispose")
        }

        fun <T: Any> Maybe<T>.doOnMisc(): Maybe<T> = doOnSubscribe {
            println("👀doOnSubscribe")
        }.doOnComplete {
            println("👀doOnComplete")
        }.doOnError {
            println("👀doOnError: $it")
        }.doOnTerminate {
            println("👀doOnTerminate")
        }.doOnDispose {
            println("👀doOnDispose")
        }

        fun <T : Any> Observable<T>.doOnMisc(): Observable<T> = doOnSubscribe {
            println("👀doOnSubscribe")
        }.doOnNext {
            println("👀doOnNext: $it")
        }.doOnComplete {
            println("👀doOnComplete")
        }.doOnError {
            println("👀doOnError: $it")
        }.doOnTerminate {
            println("👀doOnTerminate")
        }.doOnDispose {
            println("👀doOnDispose")
        }

        fun Disposable.print() {
            println("👀Disposable#isDisposed: $isDisposed")
        }
    }

    object SubscribeUtil {
        fun Completable.mySubscribe(): Disposable =
            subscribe({ println("CompletableObserver#onComplete") }) { println("CompletableObserver#onError: $it") }

        fun <T : Any> Single<T>.mySubscribe(): Disposable =
            subscribe({ println("Observer#onSuccess: $it") }, { println("Observer#onError: $it") })

        fun <T> Maybe<T>.mySubscribe(): Disposable = subscribe({ println("MaybeObserver#onNext: $it") },
            { println("MaybeObserver#onError: $it") },
            { println("MaybeObserver#onComplete") })

        fun <T : Any> Observable<T>.mySubscribe(): Disposable = subscribe({ println("Observer#onNext: $it") },
            { println("Observer#onError: $it") },
            { println("Observer#onComplete") })

        fun <T : Any> Observable<T>.mySubscribeWithId(id: Int) {
            fun <T : Any> createObserver(id: Int) = object : Observer<T> {
                val id = id
                override fun onSubscribe(d: Disposable) {
                    println("Observer[$id]#onSubscribe")
                }

                override fun onNext(item: T) {
                    println("Observer[$id]#onNext: $item")
                }

                override fun onError(e: Throwable) {
                    println("Observer[$id]#onError: $e")
                    e.printStackTrace()
                }

                override fun onComplete() {
                    println("Observer[$id]#onComplete")
                }
            }

            return subscribe(createObserver<T>(id))
        }
    }

    fun <T : Any> Observable<T>.errorWhen(elementToBeError: T): Observable<T> = flatMap {
        if (it == elementToBeError) {
            Observable.error(Throwable("WTF"))
        } else {
            Observable.just(it)
        }
    }

    /**
     * Unlike [Observable.error], throwing [Throwable] prevents calling [Observer.onNext] even on the previous items.
     */
    fun <T : Any> Observable<T>.errorWhen2(elementToBeError: T): Observable<T> = map {
        if (it != elementToBeError) {
            throw Throwable("WTF")
        }
        it
    }

    private fun <T : Any> createObservableTransformer() = ObservableTransformer<T, T> {
        it.doOnMisc()
    }
}
