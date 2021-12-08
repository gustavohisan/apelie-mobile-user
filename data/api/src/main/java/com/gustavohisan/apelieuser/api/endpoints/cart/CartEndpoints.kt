package com.gustavohisan.apelieuser.api.endpoints.cart

import com.gustavohisan.apelieuser.api.model.cart.AddCartItemsData
import com.gustavohisan.apelieuser.api.model.cart.CartItem
import com.gustavohisan.apelieuser.api.model.cart.CheckoutData
import com.gustavohisan.apelieuser.api.model.cart.EditCartItemsData
import com.gustavohisan.apelieuser.api.model.login.LoginUserData
import com.gustavohisan.apelieuser.api.model.product.Product
import com.gustavohisan.apelieuser.api.model.store.MainScreenStore
import com.gustavohisan.apelieuser.api.model.store.Store
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

internal interface CartEndpoints {

    @GET("cart")
    suspend fun getCartItems(): Response<List<CartItem>>

    @POST("cart/checkout")
    suspend fun buyCartItems(@Body cartItemsData: CheckoutData): Response<ResponseBody>

    @POST("cart")
    suspend fun addItemInCart(@Body cartItemsData: AddCartItemsData): Response<ResponseBody>

    @PUT("cart")
    suspend fun editCartItem(@Body cartItemsData: EditCartItemsData): Response<ResponseBody>

    @POST("cart/clear")
    suspend fun clearCart(): Response<ResponseBody>
}
