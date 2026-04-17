package com.tatsuyafujisaki.example.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;

/// [BiConsumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/BiConsumer.html)
/// [Consumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/Consumer.html)
/// [DoubleConsumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/DoubleConsumer.html)
/// [IntConsumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/IntConsumer.html)
/// [ObjDoubleConsumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/ObjDoubleConsumer.html)
/// [ObjIntConsumer](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/ObjIntConsumer.html)
public class ConsumerExample {
    public static void main(String[] args) {
        biConsumerExample();
        System.out.println("--");

        consumerExample();
        System.out.println("--");

        doubleConsumerExample();
        System.out.println("--");

        intConsumerExample();
        System.out.println("--");

        objDoubleConsumerExample();
        System.out.println("--");

        objIntConsumerExample();
        System.out.println("--");
    }

    private static void biConsumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        BiConsumer<String, String> consumer = (s1, s2) -> System.out.println(s1 + s2);
        consumer.andThen(consumer).accept("🍎", "🍊");
    }

    private static void consumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Consumer<String> consumer = System.out::println;
        consumer.andThen(consumer).accept("🍎");
    }

    private static void doubleConsumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        DoubleConsumer consumer = System.out::println;
        consumer.andThen(consumer).accept(1.2);
    }

    private static void intConsumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        IntConsumer consumer = System.out::println;
        consumer.andThen(consumer).accept(1);
    }

    private static void objDoubleConsumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        ObjDoubleConsumer<String> consumer = (s, x) -> System.out.println(s + x);
        consumer.accept("🍎", 1.2);
    }

    private static void objIntConsumerExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        ObjIntConsumer<String> consumer = (s, x) -> System.out.println(s + x);
        consumer.accept("🍎", 1);
    }
}
