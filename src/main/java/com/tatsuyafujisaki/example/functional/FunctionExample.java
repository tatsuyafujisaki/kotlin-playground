package com.tatsuyafujisaki.example.functional;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

/// [BiFunction](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/BiFunction.html)
/// [DoubleFunction](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/DoubleFunction.html)
/// [Function](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/Function.html)
/// [IntFunction](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/IntFunction.html)
public class FunctionExample {
    public static void main(String[] args) {
        biFunctionExample();
        System.out.println("--");

        doubleFunctionExample();
        System.out.println("--");

        functionExample();
        System.out.println("--");

        intFunctionExample();
        System.out.println("--");
    }

    private static void biFunctionExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        BiFunction<String, String, String> function = (s1, s2) -> s1 + s2;
        System.out.println(
                function
                        .andThen(s -> formatKeyValue("andThen", s))
                        .apply("🍎", "🍏")
        );
    }

    private static void doubleFunctionExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        DoubleFunction<String> function = x -> x + "🍎";
        System.out.println(function.apply(1.2));
    }

    private static void functionExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Function<String, String> function = s -> s + "🍏";
        System.out.println(
                function
                        .andThen(s -> formatKeyValue("andThen", s))
                        .compose(s -> formatKeyValue("compose", s))
                        .apply("🍎")
        );
    }

    private static void intFunctionExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        IntFunction<String> function = n -> n + "🍎";
        System.out.println(function.apply(1));
    }

    private static String formatKeyValue(String key, Object value) {
        return "(" + key + ": " + value + ")";
    }
}
