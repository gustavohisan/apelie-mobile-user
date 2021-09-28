package com.gustavohisan.apelieuser.store.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.store.LoadStoreData
import com.gustavohisan.apelieuser.store.mapper.StoreItemMapper
import com.gustavohisan.apelieuser.store.mapper.StoreStateMapper
import com.gustavohisan.apelieuser.store.model.ProductCategory
import com.gustavohisan.apelieuser.store.model.StoreItem
import com.gustavohisan.apelieuser.store.model.StoreState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class StoreViewModel(
    private val loadStoreData: LoadStoreData,
    private val storeStateMapper: StoreStateMapper,
    private val storeItemMapper: StoreItemMapper
) : ViewModel() {

    private val _storeDataState: MutableLiveData<StoreState> = MutableLiveData()
    val storeDataState: LiveData<StoreState>
        get() = _storeDataState

    fun getStoreData(storeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _storeDataState.postValue(storeStateMapper.toPresentation(loadStoreData(storeId)))
        }
    }

    fun mapCategoriesToUi(categories: List<ProductCategory>): List<StoreItem> =
        storeItemMapper.toPresentation(categories)
}
