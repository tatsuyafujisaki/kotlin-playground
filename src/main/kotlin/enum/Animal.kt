package enum

enum class Animal(emoji: String) {
    Cat(emoji = "🐈") {
        override val face = "🐱"
        override fun cry() {
            println("Meow!")
        }
    },
    Dog(emoji = "🐕") {
        override val face = "🐶"
        override fun cry() {
            println("Bark!")
        }
    };

    abstract val face: String
    abstract fun cry()
}
