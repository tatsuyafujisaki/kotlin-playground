# Note
* `Observable` is automatically disposed when `Subscriber.OnComplete()` or `Subscriber.onError()` is called.

## onErrorResumeNext
* Immediately after `Observable.onErrorResumeNext()` is called, `Observer.onNext()` and `Observer.onComplete()` will be called instead of `Observer.onError()`.
* When `Observable.onErrorResumeNext()` is inside `Observable.flatMap`, the outer `Observable` will continue as if no error occurred.
