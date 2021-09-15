package com.gustavohisan.apelieuser.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.feed.mapper.StoreStateMapper
import com.gustavohisan.apelieuser.feed.model.StoreState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class FeedViewModel(
    private val loadMainScreenStoreList: LoadMainScreenStoreList,
    private val storeStateMapper: StoreStateMapper
) : ViewModel() {

    private val _storeState: MutableLiveData<StoreState> = MutableLiveData()

    val storeState: LiveData<StoreState>
        get() = _storeState

    fun getHomeStoreList() {
        viewModelScope.launch(Dispatchers.IO) {
            _storeState.postValue(storeStateMapper.toPresentation(loadMainScreenStoreList()))
        }
    }
}
