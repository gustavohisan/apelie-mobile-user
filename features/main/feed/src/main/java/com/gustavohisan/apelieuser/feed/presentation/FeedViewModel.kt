package com.gustavohisan.apelieuser.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.store.LoadCategories
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.feed.mapper.CategoryStateMapper
import com.gustavohisan.apelieuser.feed.mapper.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.feed.model.CategoryState
import com.gustavohisan.apelieuser.feed.model.MainScreenStoreState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class FeedViewModel(
    private val loadMainScreenStoreList: LoadMainScreenStoreList,
    private val storeStateMapper: MainScreenStoreStateMapper,
    private val categoryStateMapper: CategoryStateMapper,
    private val loadCategories: LoadCategories
) : ViewModel() {

    private val _storeState: MutableLiveData<MainScreenStoreState> =
        MutableLiveData(MainScreenStoreState.Loading)
    val storeState: LiveData<MainScreenStoreState>
        get() = _storeState

    private val _categoryState: MutableLiveData<CategoryState> = MutableLiveData()
    val categoryState: LiveData<CategoryState>
        get() = _categoryState

    fun getHomeStoreList() {
        Timber.d("getHomeStoreList")
        viewModelScope.launch(Dispatchers.IO) {
            _storeState.postValue(storeStateMapper.toPresentation(loadMainScreenStoreList()))
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryState.postValue(categoryStateMapper.toPresentation(loadCategories()))
        }
    }
}
