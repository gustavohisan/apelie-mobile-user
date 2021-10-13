package com.gustavohisan.apelieuser.product.injection

import com.gustavohisan.apelieuser.main.provider.ProductProvider
import com.gustavohisan.apelieuser.product.mapper.ProductErrorTypeMapper
import com.gustavohisan.apelieuser.product.mapper.ProductMapper
import com.gustavohisan.apelieuser.product.mapper.ProductStateMapper
import com.gustavohisan.apelieuser.product.presentation.ProductViewModel
import com.gustavohisan.apelieuser.product.provider.ProductProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {

    factory<ProductProvider> { ProductProviderImpl() }
    viewModel { ProductViewModel(get(), get()) }
    factory { ProductMapper() }
    factory { ProductErrorTypeMapper() }
    factory { ProductStateMapper(get(), get()) }
}

