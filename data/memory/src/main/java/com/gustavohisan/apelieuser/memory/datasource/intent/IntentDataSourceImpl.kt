package com.gustavohisan.apelieuser.memory.datasource.intent

import com.gustavohisan.apelieuser.memory.loader.intent.MainScreenIntentLoader
import com.gustavohisan.apelieuser.repository.datasource.intent.IntentDataSource

/**
 * Implementation of [IntentDataSource].
 *
 * @param mainScreenIntentLoader loader used to load the main screen intent action
 */
internal class IntentDataSourceImpl(
    private val mainScreenIntentLoader: MainScreenIntentLoader
) : IntentDataSource {

    override fun getMainScreenIntentAction(): String =
        mainScreenIntentLoader.getMainScreenIntentAction()
}
