# Observable

is automatically disposed when `Subscriber#OnComplete()` or `Subscriber#onError(Throwable)` is called.

# Observable#onErrorResumeNext

* Immediately after `Observable#onErrorResumeNext()` is called, `Observer.onNext()` and `Observer.onComplete()` will be
  called instead of `Observer.onError()`.
* When `Observable.onErrorResumeNext()` is inside `Observable.flatMap`, the outer `Observable` will continue as if no
  error occurred.

# Observer#onComplete()

Afterwards, `Observer#onNext()` will never be called.

# Observer#onError(Throwable)

Afterwards, `Observer#onNext()` and `Observer.onComplete()` will never be called.

# Subscribe and dispose

Even after a subscription's `Disposable` is disposed (e.g. by `Observer.onError()`), you can subscribe to the
same `Observable` and it works as if no error occurred before.
