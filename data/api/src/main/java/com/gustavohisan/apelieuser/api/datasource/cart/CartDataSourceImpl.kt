package com.gustavohisan.apelieuser.api.datasource.cart

import com.gustavohisan.apelieuser.api.endpoints.cart.CartEndpoints
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.api.mapper.cart.CheckoutItemsFromCartMapper
import com.gustavohisan.apelieuser.api.mapper.cart.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.api.mapper.cart.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.api.mapper.cart.InsertProductInCartStateMapper
import com.gustavohisan.apelieuser.api.model.cart.*
import com.gustavohisan.apelieuser.api.model.cart.AddCartItemsData
import com.gustavohisan.apelieuser.api.model.cart.CheckoutData
import com.gustavohisan.apelieuser.api.model.cart.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.api.model.cart.EditCartItemsData
import com.gustavohisan.apelieuser.api.model.cart.InsertProductInCartState
import com.gustavohisan.apelieuser.repository.datasource.cart.CartDataSource
import timber.log.Timber
import com.gustavohisan.apelieuser.repository.model.cart.CheckoutItemsFromCartState as CheckoutRepoState
import com.gustavohisan.apelieuser.repository.model.cart.EditProductInCartState as EditRepoState
import com.gustavohisan.apelieuser.repository.model.cart.GetItemsFromCartState as GetItemsRepoState
import com.gustavohisan.apelieuser.repository.model.cart.InsertProductInCartState as InsertRepoState

internal class CartDataSourceImpl(
    apiFactory: ApiFactory,
    private val insertProductInCartStateMapper: InsertProductInCartStateMapper,
    private val checkoutItemsFromCartMapper: CheckoutItemsFromCartMapper,
    private val editProductInCartStateMapper: EditProductInCartStateMapper,
    private val getItemsFromCartStateMapper: GetItemsFromCartStateMapper
) : CartDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(CartEndpoints::class.java)

    override suspend fun insertProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): InsertRepoState {
        val callback = endpoint.addItemInCart(AddCartItemsData(productId, description, quantity))
        return if (callback.isSuccessful) {
            Timber.d("insertProductInCart - Success")
            insertProductInCartStateMapper.toRepo(InsertProductInCartState.Success)
        } else {
            Timber.d("insertProductInCart - Error")
            insertProductInCartStateMapper.toRepo(InsertProductInCartState.Error)
        }
    }

    override suspend fun getCartItemsFromUser(): GetItemsRepoState {
        val callback = endpoint.getCartItems()
        return if (callback.isSuccessful) {
            Timber.d("getCartItemsFromUser - Success")
            getItemsFromCartStateMapper.toRepo(GetItemsFromCartState.Success(callback.body()))
        } else {
            Timber.d("getCartItemsFromUser - Error")
            getItemsFromCartStateMapper.toRepo(GetItemsFromCartState.Error)
        }
    }

    override suspend fun checkoutItemsFromCart(
        paymentMethod: String,
        addressId: Int
    ): CheckoutRepoState {
        val callback = endpoint.buyCartItems(CheckoutData(paymentMethod, addressId))
        return if (callback.isSuccessful) {
            Timber.d("checkoutItemsFromCart - Success")
            checkoutItemsFromCartMapper.toRepo(CheckoutItemsFromCartState.Success)
        } else {
            Timber.d("checkoutItemsFromCart - Error - requestCode = ${callback.code()}")
            checkoutItemsFromCartMapper.toRepo(CheckoutItemsFromCartState.Error)
        }
    }

    override suspend fun editProductInCart(
        productId: Int,
        description: String,
        quantity: Int
    ): EditRepoState {
        val callback = endpoint.editCartItem(EditCartItemsData(productId, description, quantity))
        return if (callback.isSuccessful) {
            Timber.d("editProductInCart - Success")
            editProductInCartStateMapper.toRepo(EditProductInCartState.Success)
        } else {
            Timber.d("editProductInCart - Error - errorCode = ${callback.code()}")
            editProductInCartStateMapper.toRepo(EditProductInCartState.Error)
        }
    }

    override suspend fun clearCart(): Boolean {
        val callback = endpoint.clearCart()
        Timber.d("clearCart = ${callback.isSuccessful}")
        return callback.isSuccessful
    }
}
