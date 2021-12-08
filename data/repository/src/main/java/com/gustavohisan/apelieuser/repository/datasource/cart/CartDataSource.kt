package com.gustavohisan.apelieuser.repository.datasource.cart

import com.gustavohisan.apelieuser.repository.model.cart.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.repository.model.cart.EditProductInCartState
import com.gustavohisan.apelieuser.repository.model.cart.GetItemsFromCartState
import com.gustavohisan.apelieuser.repository.model.cart.InsertProductInCartState

interface CartDataSource {

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
