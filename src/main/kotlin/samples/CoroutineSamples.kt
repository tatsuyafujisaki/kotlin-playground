package samples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CoroutineSamples {
    suspend fun iAmMainSafe() = withContext(Dispatchers.IO) {
        // You can block stuff here.
    }

// Usage
//    viewModelScope.launch
//    {
//        iAmMainSafe()
//    }
}
