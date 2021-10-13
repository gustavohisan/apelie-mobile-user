package com.gustavohisan.apelieuser.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.feed.mapper.MainScreenStoreStateMapper
import com.gustavohisan.apelieuser.feed.model.MainScreenStoreState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class FeedViewModel(
    private val loadMainScreenStoreList: LoadMainScreenStoreList,
    private val storeStateMapper: MainScreenStoreStateMapper
) : ViewModel() {

    private val _storeState: MutableLiveData<MainScreenStoreState> =
        MutableLiveData(MainScreenStoreState.Loading)
    val storeState: LiveData<MainScreenStoreState>
        get() = _storeState

    fun getHomeStoreList() {
        Timber.d("getHomeStoreList")
        viewModelScope.launch(Dispatchers.IO) {
            _storeState.postValue(storeStateMapper.toPresentation(loadMainScreenStoreList()))
        }
    }
}
