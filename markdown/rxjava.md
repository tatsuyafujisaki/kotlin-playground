# Note
* Observable is automatically disposed when `Subscriber.OnCompleted()` or `Subscriber.onError()` is called.
* Immediately after `Observable.onErrorResumeNext()` is called, `Observer.onNext()` and `Observer.onComplete()` will be called instead of `Observer.onError()`.
