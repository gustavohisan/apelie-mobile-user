package com.gustavohisan.apelieuser.domain.usecase.cart

import com.gustavohisan.apelieuser.domain.model.cart.InsertProductInCartState
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository

class InsertProductInCart(private val cartRepository: CartRepository) {

    suspend operator fun invoke(
        productId: Int,
        description: String,
        quantity: Int
    ): InsertProductInCartState =
        cartRepository.insertProductInCart(productId, description, quantity)
}
