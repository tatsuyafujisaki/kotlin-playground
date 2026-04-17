package com.tatsuyafujisaki.example.functional;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/// [BooleanSupplier](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/BooleanSupplier.html)
/// [DoubleSupplier](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/DoubleSupplier.html)
/// [IntSupplier](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/IntSupplier.html)
/// [Supplier](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/function/Supplier.html)
public class SupplierExample {
    public static void main(String[] args) {
        booleanSupplierExample();
        System.out.println("--");

        doubleSupplierExample();
        System.out.println("--");

        intSupplierExample();
        System.out.println("--");

        supplierExample();
        System.out.println("--");
    }

    private static void booleanSupplierExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        BooleanSupplier supplier = () -> true;
        System.out.println(supplier.getAsBoolean());
    }

    private static void doubleSupplierExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        DoubleSupplier supplier = () -> 1.2;
        System.out.println(supplier.getAsDouble());
    }

    private static void intSupplierExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        IntSupplier supplier = () -> 1;
        System.out.println(supplier.getAsInt());
    }

    private static void supplierExample() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        Supplier<String> supplier = () -> "🍎";
        System.out.println(supplier.get());
    }
}
