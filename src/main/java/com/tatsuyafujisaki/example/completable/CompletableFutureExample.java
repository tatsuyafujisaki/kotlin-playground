package com.tatsuyafujisaki.example.completable;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/// [CompletableFuture](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/concurrent/CompletableFuture.html)
public class CompletableFutureExample {
    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1() {
        var methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        try {
            var data = wrapCallbackApi(CompletableFutureExample::callbackBasedApi).join();
            System.out.println(methodName + " succeeded: " + data);
        } catch (Exception e) {
            System.err.println(methodName + " failed: " + e.getCause().getMessage());
        }
    }

    private static void example2() {
        var methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        wrapCallbackApi(CompletableFutureExample::callbackBasedApi)
                .whenComplete((data, throwable) -> {
                            if (throwable == null) {
                                System.out.println(methodName + " succeeded: " + data);
                            } else {
                                System.err.println(methodName + " failed: " + throwable.getMessage());
                            }
                        }
                );
    }

    private static <T> CompletableFuture<T> wrapCallbackApi(CallbackBasedApi<T> operation) {
        var future = new CompletableFuture<T>();
        operation.execute(future::complete, future::completeExceptionally);
        return future;
    }

    private static void callbackBasedApi(Consumer<String> onSuccess, Consumer<Exception> onError) {
        try {
            mayThrowException();
            onSuccess.accept("🍎");
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    private static void mayThrowException() throws RuntimeException {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("💀");
        }
    }
}
