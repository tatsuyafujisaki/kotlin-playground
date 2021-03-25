package rx

import io.reactivex.rxjava3.core.Completable
import util.RxJavaUtil.mySubscribe
import util.RxJavaUtil.print

object CompletableSamples {
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
