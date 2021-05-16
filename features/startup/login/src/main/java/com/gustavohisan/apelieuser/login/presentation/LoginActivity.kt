package com.gustavohisan.apelieuser.login.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gustavohisan.apelieuser.core.extensions.isValidEmail
import com.gustavohisan.apelieuser.core.extensions.isValidPassword
import com.gustavohisan.apelieuser.login.R
import com.gustavohisan.apelieuser.login.databinding.LoginActivityBinding
import com.gustavohisan.apelieuser.login.model.LoginErrorType
import com.gustavohisan.apelieuser.login.model.LoginState
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Login activity.
 */
internal class LoginActivity : AppCompatActivity() {

    private val binding: LoginActivityBinding by lazy {
        LoginActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModel()

    private val loginStateObserver = Observer<LoginState> {
        when (it) {
            is LoginState.Checking -> setCheckingLayoutState()
            is LoginState.Error -> setErrorLayoutState(it.errorTypeList)
            is LoginState.Success -> openMainActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        val view = binding.root
        setContentView(view)
        setupForm()
        viewModel.loginState.observe(this, loginStateObserver)
    }

    /**
     * Sets up the login form.
     */
    private fun setupForm() {
        Timber.d("setupForm")

        binding.loginButtonSubmit.setOnClickListener {
            if (validateFields()) {
                viewModel.validateUserLogin(
                    binding.loginTextfieldEmail.text.toString(),
                    binding.loginTextfieldPassword.text.toString()
                )
            }
        }
        binding.loginTextCreateAccount.setOnClickListener {
            openRegisterActivity()
        }
    }

    /**
     * Validate both email and password fields, if they are invalid return false and update the view
     * model state.
     *
     * @return if the fields are valid
     */
    private fun validateFields(): Boolean {
        val isValidEmail = binding.loginTextfieldEmail.text.toString().isValidEmail()
        val isValidPassword = binding.loginTextfieldPassword.text.toString().isValidPassword()

        Timber.d(
            "validateFields - isValidEmail = $isValidEmail - isValidPassword = $isValidPassword"
        )

        return when {
            isValidEmail.not() || isValidPassword.not() -> {
                viewModel.setLoginState(
                    LoginState.Error(
                        listOfNotNull(
                            LoginErrorType.INVALID_EMAIL.takeUnless { isValidEmail },
                            LoginErrorType.INVALID_PASSWORD.takeUnless { isValidPassword })
                    )
                )
                false
            }
            else -> true
        }
    }

    /**
     * Sets the layout to the checking state.
     */
    private fun setCheckingLayoutState() {
        Timber.d("setCheckingLayoutState")

        binding.loginButtonSubmit.isEnabled = false
        clearTextFieldErrors()
    }

    /**
     * Sets the layout to the error state, showing every error sent by the error list.
     *
     * @param errorList the list of [LoginErrorType]
     */
    private fun setErrorLayoutState(errorList: List<LoginErrorType>) {
        Timber.d("setErrorLayoutState - errorList = $errorList")

        clearTextFieldErrors()
        enableSubmitButton()
        errorList.forEach {
            when (it) {
                LoginErrorType.INVALID_PASSWORD -> {
                    binding.loginTextfieldOutlinePassword.error =
                        resources.getString(R.string.login_error_invalid_password)
                }
                LoginErrorType.WRONG_PASSWORD -> {
                    binding.loginTextfieldOutlinePassword.error =
                        resources.getString(R.string.login_error_invalid_password)
                }
                LoginErrorType.NOT_SUBSCRIBED -> {
                    binding.loginTextfieldOutlineEmail.error =
                        resources.getString(R.string.login_error_not_subscribed)
                }
                LoginErrorType.SERVER_UNAVAILABLE -> {
                    showToastMessage(R.string.login_error_server_unavailable)
                }
                LoginErrorType.INVALID_EMAIL -> {
                    binding.loginTextfieldOutlineEmail.error =
                        resources.getString(R.string.login_error_invalid_email)
                }
            }
        }
    }

    /**
     * Shows a toast message with the string of the resource id.
     *
     * @param message a [Int] with the resource id to the string message
     */
    private fun showToastMessage(message: Int) {
        Timber.d("showToastMessage - message = $message")

        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun enableSubmitButton() {
        binding.loginButtonSubmit.isEnabled = true
    }

    private fun clearTextFieldErrors() {
        Timber.d("clearTextFieldErrors")

        binding.loginTextfieldOutlinePassword.error = null
        binding.loginTextfieldOutlineEmail.error = null
    }

    private fun openMainActivity() {
        Timber.d("openMainActivity")

        finish()
        startActivity(Intent(viewModel.getMainScreenIntent()))
    }

    private fun openRegisterActivity() {
        Timber.d("openRegisterActivity")

        startActivity(Intent(viewModel.getRegisterScreenIntent()))
    }
}
