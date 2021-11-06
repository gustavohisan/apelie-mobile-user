package com.gustavohisan.apelieuser.domain.usecase.cart

import com.gustavohisan.apelieuser.domain.model.cart.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository

class CheckoutItemsFromCart(private val cartRepository: CartRepository) {

    suspend operator fun invoke(paymentMethod: String, addressId: Int): CheckoutItemsFromCartState =
        cartRepository.checkoutItemsFromCart(paymentMethod, addressId)
}
