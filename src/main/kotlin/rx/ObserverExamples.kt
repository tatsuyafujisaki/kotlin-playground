package rx

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

object ObserverExamples {
    val observer = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onNext(item: Any) {
            println("onNext: $item")
        }

        override fun onError(t: Throwable) {
            t.printStackTrace()
        }

        override fun onComplete() {
            println("onComplete")
        }
    }
}