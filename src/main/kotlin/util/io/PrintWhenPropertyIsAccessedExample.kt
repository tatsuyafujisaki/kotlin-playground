package util.io

class PrintWhenPropertyIsAccessedExample {
    var x = ""
        get() {
            println("${object {}::class.java.enclosingMethod?.name}: $field")
            return field
        }
        set(value) {
            println("${object {}::class.java.enclosingMethod?.name}: $value")
            field = value
        }
}
