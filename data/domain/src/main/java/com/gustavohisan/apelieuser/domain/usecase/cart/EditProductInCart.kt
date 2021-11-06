package com.gustavohisan.apelieuser.domain.usecase.cart

import com.gustavohisan.apelieuser.domain.model.cart.EditProductInCartState
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository

class EditProductInCart(private val cartRepository: CartRepository) {

    suspend operator fun invoke(
        productId: Int,
        description: String,
        quantity: Int
    ): EditProductInCartState =
        cartRepository.editProductInCart(productId, description, quantity)
}
