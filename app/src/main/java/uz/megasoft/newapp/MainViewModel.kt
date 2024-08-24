package uz.megasoft.newapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    sharedPref: SharedPref,
): ViewModel() {

    val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val _startDestination: MutableStateFlow<String> = MutableStateFlow("login")
    val startDestination = _startDestination.asStateFlow()

    init {
        if (sharedPref.uid.isNotEmpty()){
            _startDestination.value = "warehouse"
        }
        _isLoading.value = false
    }
}