package util.io

class PrintWhenPropertyIsAccessedExample {
    var x = ""
        get() {
            println("${object {}.javaClass.enclosingMethod?.name}: $field")
            return field
        }
        set(value) {
            println("${object {}.javaClass.enclosingMethod?.name}: $value")
            field = value
        }
}
