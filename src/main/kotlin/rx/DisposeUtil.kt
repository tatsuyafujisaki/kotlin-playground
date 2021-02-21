package rx

import io.reactivex.rxjava3.disposables.Disposable

object DisposeUtil {
    fun Disposable.print() {
        println("Disposable.isDisposed: $isDisposed")
    }
}
