package com.gustavohisan.apelieuser.domain.repository.cart

import com.gustavohisan.apelieuser.domain.model.cart.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.domain.model.cart.EditProductInCartState
import com.gustavohisan.apelieuser.domain.model.cart.GetItemsFromCartState
import com.gustavohisan.apelieuser.domain.model.cart.InsertProductInCartState

interface CartRepository {

    suspend fun insertProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): InsertProductInCartState

    suspend fun getCartItemsFromUser(): GetItemsFromCartState

    suspend fun checkoutItemsFromCart(paymentMethod: String, addressId: Int): CheckoutItemsFromCartState

    suspend fun editProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): EditProductInCartState

    suspend fun clearCart(): Boolean
}
