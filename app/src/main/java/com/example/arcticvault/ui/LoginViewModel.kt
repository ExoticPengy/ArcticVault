package com.example.arcticvault.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)


    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = !emailResult,
            passwordError = !passwordResult
        )

        allValidationsPassed.value = emailResult && passwordResult
    }

    private fun login() {

        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        val navigateToLogin = MutableLiveData<Boolean>()

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")

                if (it.isSuccessful) {
                    loginInProgress.value = false
                    navigateToLogin.value = true
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                loginInProgress.value = false

            }

    }
}

object Validator {
    fun validateEmail(email: String): Boolean {
        // Basic email validation: checking if it contains '@' symbol
        return email.isNotEmpty() && email.contains('@')
    }

    fun validatePassword(password: String): Boolean {
        // Basic password validation: checking if it's not empty and has at least 6 characters
        return password.isNotEmpty() && password.length >= 6
    }
}


