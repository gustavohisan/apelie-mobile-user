package com.gustavohisan.apelieuser.domain.usecase.cart

import com.gustavohisan.apelieuser.domain.model.cart.GetItemsFromCartState
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository

class GetCartItemsFromUser(private val cartRepository: CartRepository) {

    suspend operator fun invoke(): GetItemsFromCartState = cartRepository.getCartItemsFromUser()
}
