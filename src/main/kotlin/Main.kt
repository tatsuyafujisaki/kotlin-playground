import converters.GsonConverterExamples
import converters.KotlinxSerializationExamples
import converters.MoshiConverterExamples
import rx.ObservableExamples

fun main() {
    GsonConverterExamples.example()
    MoshiConverterExamples.example()
    KotlinxSerializationExamples.example()

    ObservableExamples.example1()
    ObservableExamples.example2()
    ObservableExamples.example3()
    ObservableExamples.errorExample1()
    ObservableExamples.errorExample2()
    ObservableExamples.errorExample3()
    ObservableExamples.errorExample4()
    ObservableExamples.errorExample5()
    ObservableExamples.errorExample6()
}
