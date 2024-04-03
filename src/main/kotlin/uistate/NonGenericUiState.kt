package uistate

/**
 * https://kotlinlang.org/docs/sealed-classes.html#state-management-in-ui-applications
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet#6
 */
private sealed interface NonGenericUiState {
    data object Loading : NonGenericUiState
    data class Success(val data: String) : NonGenericUiState
    data class Failure(val throwable: Throwable) : NonGenericUiState
}

private fun printState(state: NonGenericUiState) {
    when (state) {
        NonGenericUiState.Loading -> println("Loading")
        is NonGenericUiState.Success -> println("Success: ${state.data}")
        is NonGenericUiState.Failure -> println("Failure: ${state.throwable}")
    }
}

private fun main() {
    printState(NonGenericUiState.Loading)
    printState(NonGenericUiState.Success("Success!"))
    printState(NonGenericUiState.Failure(Throwable("Failure!")))
}

