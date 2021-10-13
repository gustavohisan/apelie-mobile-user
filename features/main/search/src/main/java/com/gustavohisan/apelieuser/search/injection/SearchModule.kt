package com.gustavohisan.apelieuser.search.injection

import com.gustavohisan.apelieuser.main.provider.SearchProvider
import com.gustavohisan.apelieuser.search.mapper.*
import com.gustavohisan.apelieuser.search.mapper.CategoryStateErrorTypeMapper
import com.gustavohisan.apelieuser.search.mapper.CategoryStateMapper
import com.gustavohisan.apelieuser.search.mapper.SearchStoreErrorTypeMapper
import com.gustavohisan.apelieuser.search.mapper.SearchStoreMapper
import com.gustavohisan.apelieuser.search.mapper.SearchStoreStateMapper
import com.gustavohisan.apelieuser.search.presentation.SearchViewModel
import com.gustavohisan.apelieuser.search.provider.SearchProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    // Provider
    factory<SearchProvider> { SearchProviderImpl() }

    // Mappers
    factory { SearchStoreMapper() }
    factory { SearchStoreStateMapper(get(), get()) }
    factory { SearchStoreErrorTypeMapper() }
    factory { CategoryStateMapper(get()) }
    factory { CategoryStateErrorTypeMapper() }

    // ViewModel
    viewModel { SearchViewModel(get(), get(), get(), get()) }
}

