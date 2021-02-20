# Note
* `Observable` is automatically disposed when `Subscriber.OnComplete()` or `Subscriber.onError()` is called.

## onErrorResumeNext
* Immediately after `Observable.onErrorResumeNext()` is called, `Observer.onNext()` and `Observer.onComplete()` will be called instead of `Observer.onError()`.
* When `Observable.onErrorResumeNext()` is inside `Observable.flatMap`, the outer `Observable` will continue as if no error occurred.

## Subscribe and dispose
* Even after a subscription's `Disposable` is disposed (e.g. by `Observer.onError()`), you can subscribe to the same `Observable` and it works as if no error occurred before.

# AsyncSubject / BehaviorSubject / PublishSubject / ReplaySubject
```kotlin
fun demo(subject: Subject<String>) {
    val observable = subject.share()
    subject.onNext("a")
    subject.onNext("b")
    observable.subscribe {
        println("onNext: $it")
    }
    subject.onNext("c")
    subject.onNext("d")
}

println("-- AsyncSubject --")
demo(AsyncSubject.create())

println("-- PublishSubject --")
demo(PublishSubject.create())

println("-- BehaviorSubject --")
demo(BehaviorSubject.create())

println("-- ReplySubject --")
demo(ReplaySubject.create())
```

## Result
```
-- AsyncSubject --
onNext: d
-- PublishSubject --
onNext: c
onNext: d
-- BehaviorSubject --
onNext: b
onNext: c
onNext: d
-- ReplySubject --
onNext: a
onNext: b
onNext: c
onNext: d
```
