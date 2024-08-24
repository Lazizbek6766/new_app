package uz.megasoft.newapp.presentation.login

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.megasoft.possystem.utils.IViewEvent
import com.megasoft.possystem.utils.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.megasoft.possystem.utils.BaseViewModel
import kotlinx.coroutines.launch
import uz.megasoft.newapp.domain.model.User
import uz.megasoft.newapp.domain.use_case.auth.AuthUseCases
import uz.megasoft.newapp.domain.use_case.user.UserUseCases
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userUseCases: UserUseCases
) : BaseViewModel<LoginUiState, LoginUiEvent>() {

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError.asStateFlow()

    fun isErrorDismiss() {
        _isError.value = false
    }

    fun register(email: String, password: String, name: String) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val result = authUseCases.registerUseCase(email, password)
            if (result.isSuccess) {
                val user = result.getOrNull()
                if (user != null) {
                    Log.d("TAG", "register: login $user")
                    // Foydalanuvchi ma'lumotlarini saqlash
                    val userData = User(uid = user.uid, name = name, email = email)
                    val saveResult = userUseCases.saveUserUseCase(userData)
                    if (saveResult) {
                        setState { currentState.copy(isLoading = false) }
                        Log.d("TAG", "register: user $saveResult")
                    } else {
                        Log.d("TAG", "register: error $saveResult")
                        setState { currentState.copy(isLoading = false, error = "User data saving failed") }
                        _isError.value = true
                    }
                }
            } else {
                setState { currentState.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "") }
            }
        }
    }

    override fun createInitialState(): LoginUiState = LoginUiState()

    override fun onTriggerEvent(event: LoginUiEvent) {

    }
}

@Stable
data class LoginUiState(
    val isLoading: Boolean = false,
    val data:FirebaseUser? = null,
    val error: String = "",
    val errorPhoneNumber: String = "",
    val errorPassword: String = "",
) : IViewState

sealed class LoginUiEvent() : IViewEvent {
    data object NavigateToWarehouseScreen : LoginUiEvent()
}