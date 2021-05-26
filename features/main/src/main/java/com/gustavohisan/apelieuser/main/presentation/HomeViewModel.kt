package com.gustavohisan.apelieuser.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.store.LoadMainScreenStoreList
import com.gustavohisan.apelieuser.main.mapper.StoreStateMapper
import com.gustavohisan.apelieuser.main.model.StoreState
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val loadMainScreenStoreList: LoadMainScreenStoreList,
    private val storeStateMapper: StoreStateMapper
) : ViewModel() {

    private val _storeState: MutableLiveData<StoreState> = MutableLiveData()

    val storeState: LiveData<StoreState>
        get() = _storeState

    fun getHomeStoreList() {
        viewModelScope.launch {
            _storeState.postValue(storeStateMapper.toPresentation(loadMainScreenStoreList()))
        }
    }
}
