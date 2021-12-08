package com.gustavohisan.apelieuser.orders.injection

import com.gustavohisan.apelieuser.main.provider.OrderProvider
import com.gustavohisan.apelieuser.main.provider.OrdersProvider
import com.gustavohisan.apelieuser.orders.mapper.*
import com.gustavohisan.apelieuser.orders.mapper.GetUserOrdersStateMapper
import com.gustavohisan.apelieuser.orders.mapper.ItemListMapper
import com.gustavohisan.apelieuser.orders.mapper.OrderMapper
import com.gustavohisan.apelieuser.orders.mapper.OwnerMapper
import com.gustavohisan.apelieuser.orders.mapper.ProductCategoryMapper
import com.gustavohisan.apelieuser.orders.presentation.OrderViewModel
import com.gustavohisan.apelieuser.orders.presentation.OrdersViewModel
import com.gustavohisan.apelieuser.orders.provider.OrderProviderImpl
import com.gustavohisan.apelieuser.orders.provider.OrdersProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ordersModule = module {
    factory<OrdersProvider> { OrdersProviderImpl() }
    factory<OrderProvider> { OrderProviderImpl() }
    viewModel { OrdersViewModel(get(), get()) }
    viewModel { OrderViewModel(get(), get(), get()) }
    factory { GetUserOrdersStateMapper(get()) }
    factory { ItemListMapper(get()) }
    factory { OrderMapper(get(), get(), get()) }
    factory { OwnerMapper() }
    factory { ProductCategoryMapper(get()) }
    factory { ProductMapper() }
    factory { StoreMapper(get(), get()) }
    factory { AddressMapper() }
    factory { GetOrderByIdStateMapper(get()) }
}
