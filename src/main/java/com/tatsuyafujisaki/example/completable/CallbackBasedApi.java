package com.tatsuyafujisaki.example.completable;

import java.util.function.Consumer;

@FunctionalInterface
interface CallbackBasedApi<T> {
    void execute(Consumer<T> onSuccess, Consumer<Exception> onError);
}
