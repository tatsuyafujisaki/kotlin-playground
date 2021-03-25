package rx

import io.reactivex.rxjava3.core.Maybe
import util.RxJavaUtil.mySubscribe
import util.RxJavaUtil.print

object MaybeSamples {
    fun example1() {
        val maybe = Maybe.just("a")
        val disposable = maybe.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val maybe = Maybe.error<Unit>(Throwable("WTF"))
        val disposable = maybe.mySubscribe()
        disposable.print() // true
    }
}
