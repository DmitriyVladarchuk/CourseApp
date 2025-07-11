package com.example.courseapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.courseapp.domain.usecase.ValidateEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isEmailValid = MutableStateFlow(false)
    val isEmailValid: StateFlow<Boolean> = _isEmailValid

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        _isEmailValid.value = validateEmailUseCase(newEmail)
        updateLoginButtonState()
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        updateLoginButtonState()
    }

    private fun updateLoginButtonState() {
        _isLoginEnabled.value = _isEmailValid.value && _password.value.isNotEmpty()
    }
}