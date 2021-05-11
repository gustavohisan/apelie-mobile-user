package com.gustavohisan.apelieuser.domain.usecase.intent

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository

/**
 * Use-case used to load the login screen intent action.
 *
 * @param intentRepository repository used to load intents data.
 */
class LoadLoginScreenIntent(private val intentRepository: IntentRepository) {

    /**
     * Returns the login screen intent action.
     *
     * @return a [String] with the action
     */
    operator fun invoke(): String = intentRepository.loadLoginScreenIntentAction()
}
