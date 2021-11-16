package com.gustavohisan.apelieuser.addressapi.injection

import com.gustavohisan.apelieuser.addressapi.datasource.AddressDataSourceImpl
import com.gustavohisan.apelieuser.addressapi.factory.ApiFactory
import com.gustavohisan.apelieuser.addressapi.mapper.CepAddressMapper
import com.gustavohisan.apelieuser.addressapi.mapper.GetUserAddressFromCepStateMapper
import com.gustavohisan.apelieuser.repository.datasource.address.AddressDataSource
import org.koin.dsl.module

val addressApiModule = module {

    single<AddressDataSource> { AddressDataSourceImpl(get(), get()) }
    single { ApiFactory() }
    factory { CepAddressMapper() }
    factory { GetUserAddressFromCepStateMapper(get()) }
}
