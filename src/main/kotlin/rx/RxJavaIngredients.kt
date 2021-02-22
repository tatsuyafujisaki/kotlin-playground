package rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.subjects.Subject

object RxJavaIngredients {
    val observable: Observable<Any> = Observable.empty()
    val observableNever: Observable<Any> = Observable.never()
    val observableError: Observable<Any> = Observable.error(Throwable())

    private val items = listOf("a", "b", "c")

    fun createObservable(
        transformer: ObservableTransformer<String, String> = createObservableTransformer()
    ): Observable<String> = Observable.fromIterable(items).compose(transformer)

    private fun <T> createObservable(
        items: List<T>,
        transformer: ObservableTransformer<T, T>
    ) = Observable.fromIterable(items).compose(transformer)

    private fun <T> createErrorObservable(transformer: ObservableTransformer<T, T>) =
        Observable.error<T>(Throwable("WTF")).compose(transformer)

    private fun <T> createObservableTransformer() = ObservableTransformer<T, T> {
        it.doOnMisc()
    }

    fun <T> Observable<T>.doOnMisc(): Observable<T> =
        doOnSubscribe {
            println("doOnSubscribe")
        }.doOnNext {
            println("doOnNext: $it")
        }.doOnError {
            println("doOnError: $it")
        }.doOnComplete {
            println("doOnComplete")
        }

    fun <T> Subject<T>.toObservable(): Observable<T> = share().hide().doOnMisc()
}
