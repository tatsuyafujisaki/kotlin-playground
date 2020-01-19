package samples

open class Bear

class PolarBear : Bear()

class BrownBear : Bear()

class BlackBear : Bear()

fun printJapanese(bear: Bear) = when (bear) {
    is PolarBear -> println("白熊")
    is BrownBear -> println("ヒグマ")
    // is BlackBear -> println("ツキノワグマ")
    else -> println("不明な熊")
}

fun main() {
    printJapanese(PolarBear()) // 白熊
    printJapanese(BrownBear()) // ヒグマ
    printJapanese(BlackBear()) // ツキノワグマ
    printJapanese(Bear()) // 不明な熊
}

/*
 * Benefit of ADT (Abstract Data Type)
 * ADT allows for detecting an error, at compile time instead of runtime.
 */