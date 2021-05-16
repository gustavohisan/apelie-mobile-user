package com.gustavohisan.apelieuser.domain.usecase.intent

import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository

/**
 * Use-case used to load the register screen intent action.
 *
 * @param intentRepository repository used to load intents data.
 */
class LoadRegisterScreenIntent(private val intentRepository: IntentRepository) {

    /**
     * Returns the register screen intent action.
     *
     * @return a [String] with the action
     */
    operator fun invoke(): String = intentRepository.loadRegisterScreenIntentAction()
}
