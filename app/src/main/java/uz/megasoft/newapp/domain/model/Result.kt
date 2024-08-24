package uz.megasoft.newapp.domain.model


sealed interface Result<out R> {
    data object Loading: Result<Nothing>
    data class Success<out T>(val data: T) : Result<T>
    data class Error<out T>(val message: Map<String, String>) : Result<T>
}
