package datastructure

/**
 * [c.f.](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet#6)
 */
sealed interface UiState {
    object Loading : UiState
    data class Success<T>(val data: T) : UiState
    data class Failure(val throwable: Throwable) : UiState
}
