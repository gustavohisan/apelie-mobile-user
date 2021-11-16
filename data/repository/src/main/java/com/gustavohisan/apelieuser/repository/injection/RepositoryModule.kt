package com.gustavohisan.apelieuser.repository.injection

import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository
import com.gustavohisan.apelieuser.domain.repository.cart.CartRepository
import com.gustavohisan.apelieuser.domain.repository.intent.IntentRepository
import com.gustavohisan.apelieuser.domain.repository.order.OrderRepository
import com.gustavohisan.apelieuser.domain.repository.store.StoreRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.domain.repository.user.UserStorageRepository
import com.gustavohisan.apelieuser.repository.mapper.address.AddressMapper
import com.gustavohisan.apelieuser.repository.mapper.address.CepAddressMapper
import com.gustavohisan.apelieuser.repository.mapper.address.GetUserAddressFromCepStateMapper
import com.gustavohisan.apelieuser.repository.mapper.address.GetUserAddressesStateMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.*
import com.gustavohisan.apelieuser.repository.mapper.cart.CartItemMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.CheckoutItemsFromCartMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.repository.mapper.cart.InsertProductInCartStateMapper
import com.gustavohisan.apelieuser.repository.mapper.login.LoginErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.mapper.order.GetUserOrdersStateMapper
import com.gustavohisan.apelieuser.repository.mapper.order.ItemListMapper
import com.gustavohisan.apelieuser.repository.mapper.order.OrderMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterErrorTypeMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.*
import com.gustavohisan.apelieuser.repository.mapper.store.MainScreenStoreMapper
import com.gustavohisan.apelieuser.repository.mapper.store.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.repository.mapper.store.OwnerMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreMapper
import com.gustavohisan.apelieuser.repository.mapper.store.StoreStateMapper
import com.gustavohisan.apelieuser.repository.repository.address.AddressRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.cart.CartRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.intent.IntentRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.order.OrderRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.store.StoreRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserApiRepositoryImpl
import com.gustavohisan.apelieuser.repository.repository.user.UserStorageRepositoryImpl
import org.koin.dsl.module

/**
 * Module for dependency injection for the repository module.
 */
val repositoryModule = module {

    // Repository
    factory<UserApiRepository> { UserApiRepositoryImpl(get(), get(), get()) }
    factory<UserStorageRepository> { UserStorageRepositoryImpl(get()) }
    factory<IntentRepository> { IntentRepositoryImpl(get()) }
    factory<StoreRepository> { StoreRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<CartRepository> { CartRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<AddressRepository> { AddressRepositoryImpl(get(), get(), get(), get()) }
    factory<OrderRepository> { OrderRepositoryImpl(get(), get()) }

    // Mapper
    factory { LoginStateMapper(get()) }
    factory { LoginErrorTypeMapper() }
    factory { RegisterStateMapper(get()) }
    factory { RegisterErrorTypeMapper() }
    factory { MainScreenStoreStateMapper(get()) }
    factory { MainScreenStoreMapper() }
    factory { StoreMapper(get(), get()) }
    factory { StoreStateMapper(get()) }
    factory { OwnerMapper() }
    factory { ProductMapper() }
    factory { ProductStateMapper(get()) }
    factory { CategoryStateMapper() }
    factory { CartItemMapper(get()) }
    factory { CheckoutItemsFromCartMapper() }
    factory { EditProductInCartStateMapper() }
    factory { GetItemsFromCartStateMapper(get()) }
    factory { InsertProductInCartStateMapper() }
    factory { AddressMapper() }
    factory { CepAddressMapper() }
    factory { GetUserAddressesStateMapper(get()) }
    factory { GetUserAddressFromCepStateMapper(get()) }
    factory { GetUserOrdersStateMapper(get()) }
    factory { ItemListMapper(get()) }
    factory { OrderMapper(get(), get()) }
}
