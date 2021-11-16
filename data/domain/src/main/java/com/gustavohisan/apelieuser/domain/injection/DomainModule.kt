package com.gustavohisan.apelieuser.domain.injection

import com.gustavohisan.apelieuser.domain.usecase.address.*
import com.gustavohisan.apelieuser.domain.usecase.cart.CheckoutItemsFromCart
import com.gustavohisan.apelieuser.domain.usecase.cart.EditProductInCart
import com.gustavohisan.apelieuser.domain.usecase.cart.GetCartItemsFromUser
import com.gustavohisan.apelieuser.domain.usecase.cart.InsertProductInCart
import com.gustavohisan.apelieuser.domain.usecase.login.LogoutUser
import com.gustavohisan.apelieuser.domain.usecase.login.ValidateLogin
import com.gustavohisan.apelieuser.domain.usecase.order.LoadOrder
import com.gustavohisan.apelieuser.domain.usecase.order.LoadUserOrders
import com.gustavohisan.apelieuser.domain.usecase.product.LoadProductData
import com.gustavohisan.apelieuser.domain.usecase.register.RegisterUser
import com.gustavohisan.apelieuser.domain.usecase.search.SearchStores
import com.gustavohisan.apelieuser.domain.usecase.splash.CheckIfUserIsLoggedIn
import com.gustavohisan.apelieuser.domain.usecase.store.LoadCategories
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.domain.usecase.store.LoadStoreData
import org.koin.dsl.module

/**
 * Module for dependency injection for the domain module.
 */
val domainModule = module {

    // Use-case
    factory { ValidateLogin(get(), get()) }
    factory { CheckIfUserIsLoggedIn(get(), get()) }
    factory { RegisterUser(get()) }
    factory { LoadMainScreenStoreList(get()) }
    factory { LoadStoreData(get()) }
    factory { SearchStores(get()) }
    factory { LoadProductData(get()) }
    factory { LoadCategories(get()) }
    factory { CheckoutItemsFromCart(get()) }
    factory { EditProductInCart(get()) }
    factory { GetCartItemsFromUser(get()) }
    factory { InsertProductInCart(get()) }
    factory { LogoutUser(get()) }
    factory { DeleteAddress(get()) }
    factory { GetUserAddressList(get()) }
    factory { InsertAddress(get()) }
    factory { SearchAddressByCep(get()) }
    factory { UpdateAddress(get()) }
    factory { LoadOrder() }
    factory { LoadUserOrders(get()) }
}
