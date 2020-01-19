package rx.example

import io.reactivex.rxjava3.core.Single
import util.RxUtil.DoOn.print
import util.RxUtil.SubscribeUtil.mySubscribe

object SingleExample {
    fun example1() {
        val single = Single.just("a")
        val disposable = single.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val single = Single.error<Unit>(Throwable("WTF"))
        val disposable = single.mySubscribe()
        disposable.print() // true
    }
}
