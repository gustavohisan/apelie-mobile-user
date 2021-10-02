package com.gustavohisan.apelieuser.product.injection

import com.gustavohisan.apelieuser.main.provider.ProductProvider
import com.gustavohisan.apelieuser.product.provider.ProductProviderImpl
import org.koin.dsl.module

val productModule = module {

    factory<ProductProvider> { ProductProviderImpl() }
}

