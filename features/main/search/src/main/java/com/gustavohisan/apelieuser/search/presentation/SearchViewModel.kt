package com.gustavohisan.apelieuser.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.search.SearchStores
import com.gustavohisan.apelieuser.domain.usecase.store.LoadCategories
import com.gustavohisan.apelieuser.search.mapper.CategoryStateMapper
import com.gustavohisan.apelieuser.search.mapper.SearchStoreStateMapper
import com.gustavohisan.apelieuser.search.model.CategoryState
import com.gustavohisan.apelieuser.search.model.Filter
import com.gustavohisan.apelieuser.search.model.SearchStoresState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class SearchViewModel(
    private val searchStore: SearchStores,
    private val searchStoreStateMapper: SearchStoreStateMapper,
    private val loadCategories: LoadCategories,
    private val categoryStateMapper: CategoryStateMapper
) : ViewModel() {

    private val _searchState: MutableLiveData<SearchStoresState> =
        MutableLiveData(SearchStoresState.NoQuery)
    val searchState: LiveData<SearchStoresState>
        get() = _searchState

    private val _categoryState: MutableLiveData<CategoryState> = MutableLiveData()
    val categoryState: LiveData<CategoryState>
        get() = _categoryState

    fun searchStores(query: String, categories: List<Filter>) {
        _searchState.value = SearchStoresState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _searchState.postValue(
                searchStoreStateMapper.toPresentation(
                    searchStore(
                        query,
                        categories.filter { it.enabled.value }.map { it.name }
                    )
                )
            )
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryState.postValue(categoryStateMapper.toPresentation(loadCategories()))
        }
    }

    fun setNoQueryState() {
        _searchState.value = SearchStoresState.NoQuery
    }
}
