package com.gustavohisan.apelieuser.memory.datasource.intent

import com.gustavohisan.apelieuser.memory.loader.intent.LoginScreenIntentLoader
import com.gustavohisan.apelieuser.memory.loader.intent.MainScreenIntentLoader
import com.gustavohisan.apelieuser.memory.loader.intent.RegisterScreenIntentLoader
import com.gustavohisan.apelieuser.repository.datasource.intent.IntentDataSource

/**
 * Implementation of [IntentDataSource].
 *
 * @param mainScreenIntentLoader loader used to load the main screen intent action
 * @param loginScreenIntentLoader loader used to load the login screen intent action
 * @param registerScreenIntentLoader loader used to load the register screen intent action
 */
internal class IntentDataSourceImpl(
    private val mainScreenIntentLoader: MainScreenIntentLoader,
    private val loginScreenIntentLoader: LoginScreenIntentLoader,
    private val registerScreenIntentLoader: RegisterScreenIntentLoader
) : IntentDataSource {

    override fun getMainScreenIntentAction(): String =
        mainScreenIntentLoader.getMainScreenIntentAction()

    override fun getLoginScreenIntentAction(): String =
        loginScreenIntentLoader.getLoginScreenIntentAction()

    override fun getRegisterScreenIntentAction(): String =
        registerScreenIntentLoader.getRegisterScreenIntentAction()
}
