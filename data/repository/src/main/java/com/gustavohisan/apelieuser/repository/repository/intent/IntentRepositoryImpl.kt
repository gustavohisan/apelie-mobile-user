package com.gustavohisan.apelieuser.repository.repository.intent

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository
import com.gustavohisan.apelieuser.repository.datasource.intent.IntentDataSource

/**
 * Implementation of [IntentRepository].
 *
 * @param intentDataSource data source used to load the intents information
 */
internal class IntentRepositoryImpl(
    private val intentDataSource: IntentDataSource
) : IntentRepository {

    override fun loadMainScreenIntentAction(): String =
        intentDataSource.getMainScreenIntentAction()

    override fun loadLoginScreenIntentAction(): String =
        intentDataSource.getLoginScreenIntentAction()
}
