package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer

object ObservableFactory {
    val observable1: Observable<Any> = Observable.empty()
    val observable2: Observable<Any> = Observable.never()
    val observable3: Observable<Any> = Observable.error(Throwable())

    private val items = listOf("apple", "banana", "orange")

    fun createObservable(
        transformer: ObservableTransformer<String, String> = createObservableTransformer()
    ): Observable<String> = Observable.fromIterable(items).compose(transformer)

    private fun <T> createObservable(
        items: List<T>,
        transformer: ObservableTransformer<T, T> = createObservableTransformer()
    ) = Observable.fromIterable(items).compose(transformer)

    fun <T> createErrorObservable(transformer: ObservableTransformer<T, T> = createObservableTransformer()) =
        Observable.error<T>(Throwable("wtf")).compose(transformer)

    private fun <T> createObservableTransformer() = ObservableTransformer<T, T> { observable ->
        observable
            .doOnSubscribe {
                println("Observable.doOnSubscribe")
            }
            .doOnNext {
                println("Observable.doOnNext: $it")
            }
            .doOnError {
                println("Observable.doOnError: $it")
            }
            .doOnComplete {
                println("Observable.doOnComplete")
            }
    }
}
