package com.gustavohisan.apelieuser.store.injection

import com.gustavohisan.apelieuser.main.provider.StoreProvider
import com.gustavohisan.apelieuser.store.mapper.*
import com.gustavohisan.apelieuser.store.mapper.OwnerMapper
import com.gustavohisan.apelieuser.store.mapper.ProductCategoryMapper
import com.gustavohisan.apelieuser.store.mapper.ProductMapper
import com.gustavohisan.apelieuser.store.mapper.StoreErrorTypeMapper
import com.gustavohisan.apelieuser.store.mapper.StoreMapper
import com.gustavohisan.apelieuser.store.mapper.StoreStateMapper
import com.gustavohisan.apelieuser.store.presentation.StoreViewModel
import com.gustavohisan.apelieuser.store.provider.StoreProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storeModule = module {

    viewModel { StoreViewModel(get(), get(), get()) }
    factory { OwnerMapper() }
    factory { StoreMapper(get(), get()) }
    factory { StoreStateMapper(get(), get()) }
    factory { StoreErrorTypeMapper() }
    factory { ProductMapper() }
    factory { StoreItemMapper() }
    factory { ProductCategoryMapper(get()) }
    factory<StoreProvider> { StoreProviderImpl() }
}

