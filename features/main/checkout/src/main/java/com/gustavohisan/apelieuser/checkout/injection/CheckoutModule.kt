package com.gustavohisan.apelieuser.checkout.injection

import com.gustavohisan.apelieuser.checkout.mapper.AddressMapper
import com.gustavohisan.apelieuser.checkout.mapper.CheckoutItemsFromCartStateMapper
import com.gustavohisan.apelieuser.checkout.mapper.GetAddressStateMapper
import com.gustavohisan.apelieuser.checkout.presentation.CheckoutViewModel
import com.gustavohisan.apelieuser.checkout.provider.CheckoutProviderImpl
import com.gustavohisan.apelieuser.main.provider.CheckoutProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val checkoutModule = module {
    viewModel { CheckoutViewModel(get(), get(), get(), get()) }
    factory { AddressMapper() }
    factory { CheckoutItemsFromCartStateMapper() }
    factory { GetAddressStateMapper(get()) }
    factory<CheckoutProvider> { CheckoutProviderImpl() }
}
