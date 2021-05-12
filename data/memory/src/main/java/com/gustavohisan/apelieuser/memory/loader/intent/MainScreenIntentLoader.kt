package com.gustavohisan.apelieuser.memory.loader.intent

/**
 * Loader used to retrieve the main screen intent action.
 */
internal class MainScreenIntentLoader {

    /**
     * Returns the main screen intent action.
     *
     * @return a [String] with the action
     */
    fun getMainScreenIntentAction(): String = INTENT_ACTIOn

    private companion object {

        private const val INTENT_ACTIOn = "com.gustavohisan.apelieuser.START_MAIN"
    }
}
