package rx.example

import io.reactivex.rxjava3.core.Completable
import util.RxUtil.DoOn.print
import util.RxUtil.SubscribeUtil.mySubscribe

object CompletableExample {
    fun example1() {
        val completable = Completable.complete()
        val disposable = completable.mySubscribe()
        disposable.print() // true
    }

    fun example2() {
        val completable = Completable.error(Throwable("WTF"))
        val disposable = completable.mySubscribe()
        disposable.print() // true
    }
}
