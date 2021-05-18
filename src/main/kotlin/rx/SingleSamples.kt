package rx

import io.reactivex.rxjava3.core.Single
import util.RxJavaUtil.DoOn.print
import util.RxJavaUtil.SubscribeUtil.mySubscribe

object SingleSamples {
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
