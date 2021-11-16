package com.gustavohisan.apelieuser.address.injection

import com.gustavohisan.apelieuser.address.mapper.CepAddressMapper
import com.gustavohisan.apelieuser.address.mapper.GetAddressFromCepStateMapper
import com.gustavohisan.apelieuser.address.mapper.InsertAddressStateErrorTypeMapper
import com.gustavohisan.apelieuser.address.mapper.InsertAddressStateMapper
import com.gustavohisan.apelieuser.address.presentation.AddressViewModel
import com.gustavohisan.apelieuser.address.provider.AddressProviderImpl
import com.gustavohisan.apelieuser.main.provider.AddressProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {
    viewModel { AddressViewModel(get(), get(), get(), get()) }
    factory { CepAddressMapper() }
    factory { GetAddressFromCepStateMapper(get()) }
    factory<AddressProvider> { AddressProviderImpl() }
    factory { InsertAddressStateMapper(get()) }
    factory { InsertAddressStateErrorTypeMapper() }
}
