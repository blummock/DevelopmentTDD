package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    private val validateEmail: UiValidator,
    private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel() {

    fun login(email: String, password: String) {
        val emailIsValid = validateEmail.isValid(email)
        val passwordIsValid = validatePassword.isValid(password)
        when {
            !emailIsValid && !passwordIsValid -> {
                communication.map(
                    LoginState.TwoErrors(
                        loginError = validateEmail.errorMessage(),
                        passwordError = validatePassword.errorMessage()
                    )
                )
            }

            !emailIsValid -> {
                communication.map(LoginState.EmailError(validateEmail.errorMessage()))
            }

            !passwordIsValid -> {
                communication.map(LoginState.PasswordError(validatePassword.errorMessage()))
            }

            else -> requestWeather()
        }
    }

    fun requestWeather() {
        viewModelScope.launch(dispatchers.io()) {
            when (val result = interactor.login()) {
                is WeatherItem.Basic -> {
                    communication.map(
                        LoginState.Success(
                            mapper.map(
                                feelsLike = result.feelsLike,
                                description = result.description,
                                temp = result.temp
                            )
                        )
                    )
                }

                is WeatherItem.Error -> communication.map(
                    LoginState.Error(mapper.map(result.exceptionType))
                )
            }
        }
    }
}
