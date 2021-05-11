package com.gustavohisan.apelieuser.memory.datasource.intent

import com.gustavohisan.apelieuser.memory.loader.intent.LoginScreenIntentLoader
import com.gustavohisan.apelieuser.memory.loader.intent.MainScreenIntentLoader
import com.gustavohisan.apelieuser.repository.datasource.intent.IntentDataSource

/**
 * Implementation of [IntentDataSource].
 *
 * @param mainScreenIntentLoader loader used to load the main screen intent action
 * @param loginScreenIntentLoader loader used to load the login screen intent action
 */
internal class IntentDataSourceImpl(
    private val mainScreenIntentLoader: MainScreenIntentLoader,
    private val loginScreenIntentLoader: LoginScreenIntentLoader
) : IntentDataSource {

    override fun getMainScreenIntentAction(): String =
        mainScreenIntentLoader.getMainScreenIntentAction()

    override fun getLoginScreenIntentAction(): String =
        loginScreenIntentLoader.getLoginScreenIntentAction()
}
