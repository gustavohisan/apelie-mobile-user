package com.gustavohisan.apelieuser.feed.injection

import com.gustavohisan.apelieuser.feed.mapper.MainScreenStoreErrorTypeMapper
import com.gustavohisan.apelieuser.feed.mapper.MainScreenStoreMapper
import com.gustavohisan.apelieuser.feed.mapper.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.feed.presentation.FeedViewModel
import com.gustavohisan.apelieuser.feed.provider.FeedProviderImpl
import com.gustavohisan.apelieuser.main.provider.FeedProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for dependency injection for the main screen module.
 */
val feedModule = module {
    viewModel { FeedViewModel(get(), get()) }
    factory { MainScreenStoreStateMapper(get(), get()) }
    factory { MainScreenStoreErrorTypeMapper() }
    factory { MainScreenStoreMapper() }
    factory<FeedProvider> { FeedProviderImpl() }
}
