package com.gustavohisan.apelieuser.register.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gustavohisan.apelieuser.core.extensions.isValidEmail
import com.gustavohisan.apelieuser.core.extensions.isValidName
import com.gustavohisan.apelieuser.core.extensions.isValidPassword
import com.gustavohisan.apelieuser.register.R
import com.gustavohisan.apelieuser.register.databinding.RegisterActivityBinding
import com.gustavohisan.apelieuser.register.model.RegisterErrorType
import com.gustavohisan.apelieuser.register.model.RegisterState
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Register activity.
 */
internal class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { RegisterActivityBinding.inflate(layoutInflater) }

    private val viewModel: RegisterViewModel by viewModel()

    private val registerStateObserver = Observer<RegisterState> {
        when (it) {
            is RegisterState.Checking -> setCheckingLayoutState()
            is RegisterState.Error -> setErrorLayoutState(it.errorTypeList)
            is RegisterState.Success -> finishRegister()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        val view = binding.root
        setContentView(view)
        setupToolbar()
        setupForm()
        viewModel.registerState.observe(this, registerStateObserver)
    }

    private fun setupToolbar() {
        Timber.d("setupToolbar")

        setSupportActionBar(binding.registerToolbar)
        supportActionBar?.setTitle(R.string.register_toolbar_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        Timber.d("onSupportNavigateUp")

        finish()
        return true
    }

    private fun setupForm() {
        Timber.d("setupForm")

        binding.registerButtonSubmit.setOnClickListener {
            if (validateFields()) {
                viewModel.registerNewUser(
                    binding.registerTextfieldEmail.text.toString(),
                    binding.registerTextfieldPassword.text.toString(),
                    binding.registerTextfieldName.text.toString()
                )
            }
        }
    }

    /**
     * Validate the layout fields, if they are invalid return false and update the view model state.
     *
     * @return if the fields are valid
     */
    private fun validateFields(): Boolean {
        val isValidEmail = binding.registerTextfieldEmail.text.toString().isValidEmail()
        val isValidPassword = binding.registerTextfieldPassword.text.toString().isValidPassword()
        val doPasswordsMatch = binding.registerTextfieldPassword.text.toString() ==
            binding.registerTextfieldConfirmPassword.text.toString()
        val isValidName = binding.registerTextfieldName.text.toString().isValidName()

        Timber.d(
            "validateFields - isValidEmail = $isValidEmail - " +
                "isValidPassWord = $isValidPassword - doPasswordsMatch = $doPasswordsMatch - " +
                "isValidName = $isValidName"
        )

        return when {
            isValidEmail.not() || isValidPassword.not() ||
                doPasswordsMatch.not() || isValidName.not() -> {
                viewModel.setRegisterState(
                    RegisterState.Error(
                        listOfNotNull(
                            RegisterErrorType.INVALID_EMAIL.takeUnless { isValidEmail },
                            RegisterErrorType.INVALID_PASSWORD.takeUnless { isValidPassword },
                            RegisterErrorType.PASSWORDS_DONT_MATCH.takeUnless { doPasswordsMatch },
                            RegisterErrorType.INVALID_NAME.takeUnless { isValidName }
                        )
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

        binding.registerButtonSubmit.isEnabled = false
        clearTextFieldErrors()
    }

    /**
     * Sets the layout to the error state, showing every error sent by the error list.
     *
     * @param errorList the list of [RegisterErrorType]
     */
    private fun setErrorLayoutState(errorList: List<RegisterErrorType>) {
        Timber.d("setErrorLayoutState - errorList = $errorList")

        clearTextFieldErrors()
        enableSubmitButton()
        errorList.forEach {
            when (it) {
                RegisterErrorType.INVALID_PASSWORD -> {
                    binding.registerTextfieldOutlinePassword.error =
                        resources.getString(R.string.register_error_invalid_password)
                }
                RegisterErrorType.INVALID_NAME -> {
                    binding.registerTextfieldOutlineName.error =
                        resources.getString(R.string.register_error_invalid_name)
                }
                RegisterErrorType.PASSWORDS_DONT_MATCH -> {
                    binding.registerTextfieldOutlineConfirmPassword.error =
                        resources.getString(R.string.register_error_passwords_dont_match)
                }
                RegisterErrorType.SERVER_UNAVAILABLE -> {
                    showToastMessage(R.string.register_error_server_unavailable)
                }
                RegisterErrorType.INVALID_EMAIL -> {
                    binding.registerTextfieldOutlineEmail.error =
                        resources.getString(R.string.register_error_invalid_email)
                }
                RegisterErrorType.ALREADY_SUBSCRIBED -> {
                    binding.registerTextfieldOutlineEmail.error =
                        resources.getString(R.string.register_error_email_already_subscribed)
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
        binding.registerButtonSubmit.isEnabled = true
    }

    private fun clearTextFieldErrors() {
        Timber.d("clearTextFieldErrors")

        binding.registerTextfieldOutlineName.error = null
        binding.registerTextfieldOutlineConfirmPassword.error = null
        binding.registerTextfieldOutlinePassword.error = null
        binding.registerTextfieldOutlineEmail.error = null
    }

    private fun finishRegister() {
        Timber.d("finishRegister")

        showToastMessage(R.string.register_successful)
        finish()
    }
}
