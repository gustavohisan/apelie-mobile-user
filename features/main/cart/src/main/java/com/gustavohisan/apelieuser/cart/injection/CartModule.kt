package com.gustavohisan.apelieuser.cart.injection

import com.gustavohisan.apelieuser.cart.mapper.CartItemMapper
import com.gustavohisan.apelieuser.cart.mapper.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.cart.mapper.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.cart.mapper.ProductMapper
import com.gustavohisan.apelieuser.cart.presentation.CartViewModel
import com.gustavohisan.apelieuser.cart.provider.CartProviderImpl
import com.gustavohisan.apelieuser.main.provider.CartProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {

    factory<CartProvider> { CartProviderImpl() }
    viewModel { CartViewModel(get(), get(), get(), get(), get()) }
    factory { CartItemMapper(get()) }
    factory { ProductMapper() }
    factory { GetItemsFromCartStateMapper(get()) }
    factory { EditProductInCartStateMapper() }
}
