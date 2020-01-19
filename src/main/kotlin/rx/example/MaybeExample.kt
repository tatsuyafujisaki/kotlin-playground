package rx.example

import io.reactivex.rxjava3.core.Maybe
import util.RxUtil.DoOn.print
import util.RxUtil.SubscribeUtil.mySubscribe

object MaybeExample {
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
