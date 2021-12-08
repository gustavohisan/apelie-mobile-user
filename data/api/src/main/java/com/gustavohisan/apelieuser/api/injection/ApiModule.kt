package com.gustavohisan.apelieuser.api.injection

import com.gustavohisan.apelieuser.api.authenticator.TokenAuthenticator
import com.gustavohisan.apelieuser.api.datasource.cart.CartDataSourceImpl
import com.gustavohisan.apelieuser.api.datasource.store.StoreDataSourceImpl
import com.gustavohisan.apelieuser.api.datasource.user.UserApiDataSourceImpl
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.api.interceptor.NetworkInterceptor
import com.gustavohisan.apelieuser.api.mapper.address.AddressMapper
import com.gustavohisan.apelieuser.api.mapper.address.GetUserAddressesStateMapper
import com.gustavohisan.apelieuser.api.mapper.cart.*
import com.gustavohisan.apelieuser.api.mapper.cart.CartItemMapper
import com.gustavohisan.apelieuser.api.mapper.cart.CheckoutItemsFromCartMapper
import com.gustavohisan.apelieuser.api.mapper.cart.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.api.mapper.cart.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.api.mapper.cart.InsertProductInCartStateMapper
import com.gustavohisan.apelieuser.api.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.api.mapper.order.GetOrderByIdStateMapper
import com.gustavohisan.apelieuser.api.mapper.order.GetUserOrdersStateMapper
import com.gustavohisan.apelieuser.api.mapper.order.ItemListMapper
import com.gustavohisan.apelieuser.api.mapper.order.OrderMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.api.mapper.store.*
import com.gustavohisan.apelieuser.repository.datasource.cart.CartDataSource
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.datasource.store.StoreDataSource
import org.koin.dsl.module

/**
 * Module for dependency injection for the api module.
 */
val apiModule = module {

    // Data source
    factory<UserApiDataSource> { UserApiDataSourceImpl(get(), get(), get(), get(), get(), get()) }
    factory<StoreDataSource> { StoreDataSourceImpl(get(), get(), get(), get()) }
    factory<CartDataSource> { CartDataSourceImpl(get(), get(), get(), get(), get())}

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
    factory { MainScreenStoreStateMapper(get()) }
    factory { MainScreenStoreMapper() }
    factory { StoreStateMapper(get()) }
    factory { StoreMapper(get(), get()) }
    factory { OwnerMapper() }
    factory { ProductMapper() }
    factory { ProductStateMapper(get()) }
    factory { CartItemMapper(get()) }
    factory { CheckoutItemsFromCartMapper() }
    factory { EditProductInCartStateMapper() }
    factory { GetItemsFromCartStateMapper(get()) }
    factory { InsertProductInCartStateMapper() }
    factory { AddressMapper() }
    factory { GetUserAddressesStateMapper(get()) }
    factory { GetUserOrdersStateMapper(get()) }
    factory { ItemListMapper(get()) }
    factory { OrderMapper(get(), get(), get()) }
    factory { GetOrderByIdStateMapper(get()) }

    // Provider
    single { ApiFactory(get(), get()) }

    // Interceptors
    factory { NetworkInterceptor() }

    factory { TokenAuthenticator() }
}
