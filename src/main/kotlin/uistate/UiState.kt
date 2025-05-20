package uistate

/**
 * https://kotlinlang.org/docs/sealed-classes.html#state-management-in-ui-applications
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet#6
 */
private sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Failure(val throwable: Throwable) : UiState<Nothing>
}

private fun printState(state: UiState<String>) {
    when (state) {
        UiState.Loading -> println("Loading")
        is UiState.Success -> println("Success: ${state.data}")
        is UiState.Failure -> println("Failure: ${state.throwable}")
    }
}

private fun main() {
    printState(UiState.Loading)
    printState(UiState.Success("Success!"))
    printState(UiState.Failure(Throwable("Failure!")))
}
