package com.tatsuyafujisaki.example.functional;

import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/// [BiPredicate](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/BiPredicate.html)
/// [DoublePredicate](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/DoublePredicate.html)
/// [IntPredicate](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/IntPredicate.html)
/// [Predicate](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/Predicate.html)
public class PredicateExample {
    public static void main(String[] args) {
        biPredicateExample();
        System.out.println("--");

        doublePredicateExample();
        System.out.println("--");

        predicateExample();
        System.out.println("--");

        intPredicateExample();
        System.out.println("--");
    }

    @SuppressWarnings("ConstantConditions")
    private static void biPredicateExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        BiPredicate<String, String> predicate = (s1, s2) -> s1.equals("🍎") && s2.equals("🍊");
        System.out.println(predicate.and(predicate).or(predicate).negate().test("🍎", "🍊"));
    }

    @SuppressWarnings("ConstantConditions")
    private static void doublePredicateExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        DoublePredicate predicate = x -> x >= 0.0 && x <= 10.0;
        System.out.println(predicate.and(predicate).or(predicate).negate().test(1.2));
    }

    @SuppressWarnings("ConstantConditions")
    private static void intPredicateExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        IntPredicate predicate = n -> n == 1;
        System.out.println(predicate.and(predicate).or(predicate).negate().test(1));
    }

    @SuppressWarnings("ConstantConditions")
    private static void predicateExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Predicate<String> predicate = (s) -> s.equals("🍎");
        System.out.println(predicate.and(predicate).or(predicate).negate().test("🍎"));
    }
}
