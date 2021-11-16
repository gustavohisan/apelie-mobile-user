package com.gustavohisan.apelieuser.domain.usecase.login

import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository

class LogoutUser(private val userStorageRepository: UserStorageRepository) {

    suspend operator fun invoke(): Boolean = userStorageRepository.removeUserToken()
}
