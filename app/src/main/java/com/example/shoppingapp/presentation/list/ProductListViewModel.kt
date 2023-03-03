package com.example.shoppingapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.product.GetProductListUseCase
import com.example.shoppingapp.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
): BaseViewModel() {

    private var _productListStateLiveData = MutableLiveData<ProductListState>(ProductListState.UnInitialized)
    val productListStateLiveData: LiveData<ProductListState> = _productListStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _productListStateLiveData.postValue(ProductListState.Loading)
        _productListStateLiveData.postValue(ProductListState.Success(getProductListUseCase()))
    }


}