package com.gustavohisan.apelieuser.domain.usecase.intent

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository

/**
 * Use-case used to load the main screen intent action.
 *
 * @param intentRepository repository used to load intents data.
 */
class LoadMainScreenIntent(private val intentRepository: IntentRepository) {

    /**
     * Returns the main screen intent action.
     *
     * @return a [String] with the action
     */
    operator fun invoke(): String = intentRepository.loadMainScreenIntentAction()
}
