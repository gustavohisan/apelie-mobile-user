package com.gustavohisan.apelieuser.domain.usecase.cart

import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository

class ClearCart(private val cartRepository: CartRepository) {

    suspend operator fun invoke(): Boolean = cartRepository.clearCart()
}
