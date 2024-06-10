private fun main() {
    val s = "🍏🍊🍏"

    // Replaces all 🍏 with 🍎.
    println(s.replace("🍏", "🍎")) // 🍎🍊🍎

    // Replaces the first character with 🍎 if it is 🍏.
    println(s.replace("^🍏".toRegex(), "🍎")) // 🍎🍊🍏

    // Replaces the last character wth 🍎 if it is 🍏.
    println(s.replace("🍏$".toRegex(), "🍎")) // 🍏🍊🍎
}