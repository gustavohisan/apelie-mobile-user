package com.gustavohisan.apelieuser.repository.repository.cart

import com.gustavohisan.apelieuser.domain.model.cart.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.domain.model.cart.EditProductInCartState
import com.gustavohisan.apelieuser.domain.model.cart.GetItemsFromCartState
import com.gustavohisan.apelieuser.domain.model.cart.InsertProductInCartState
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository
import com.gustavohisan.apelieuser.repository.datasource.cart.CartDataSource
import com.gustavohisan.apelieuser.repository.mapper.cart.CheckoutItemsFromCartMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.InsertProductInCartStateMapper

internal class CartRepositoryImpl(
    private val cartDataSource: CartDataSource,
    private val insertProductInCartStateMapper: InsertProductInCartStateMapper,
    private val getItemsFromCartStateMapper: GetItemsFromCartStateMapper,
    private val checkoutItemsFromCartMapper: CheckoutItemsFromCartMapper,
    private val editProductInCartStateMapper: EditProductInCartStateMapper
) : CartRepository {

    override suspend fun insertProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): InsertProductInCartState = insertProductInCartStateMapper.toDomain(
        cartDataSource.insertProductInCart(
            productId,
            description,
            quantity
        )
    )

    override suspend fun getCartItemsFromUser(): GetItemsFromCartState =
        getItemsFromCartStateMapper.toDomain(cartDataSource.getCartItemsFromUser())

    override suspend fun checkoutItemsFromCart(
        paymentMethod: String,
        addressId: Int
    ): CheckoutItemsFromCartState = checkoutItemsFromCartMapper.toDomain(
        cartDataSource.checkoutItemsFromCart(
            paymentMethod,
            addressId
        )
    )

    override suspend fun editProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): EditProductInCartState = editProductInCartStateMapper.toDomain(
        cartDataSource.editProductInCart(
            productId,
            description,
            quantity
        )
    )

    override suspend fun clearCart(): Boolean = cartDataSource.clearCart()
}
