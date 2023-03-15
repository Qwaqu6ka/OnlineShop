package com.example.onlineshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.api.Repository
import com.example.onlineshop.models.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel() {

    private var repository = Repository()

    private val _dataIsReady: MutableLiveData<Boolean> = MutableLiveData(false)
    val dataIsReady: LiveData<Boolean>
        get() = _dataIsReady

    private val _latestProductList: MutableLiveData<List<ProductModel>> = MutableLiveData(listOf())
    val latestProductList: LiveData<List<ProductModel>>
        get() = _latestProductList

    private val _saleProductList: MutableLiveData<List<ProductModel>> = MutableLiveData(listOf())
    val saleProductList: LiveData<List<ProductModel>>
        get() = _saleProductList

    init {
        viewModelScope.launch(Dispatchers.Main) {
            launch {
                _latestProductList.value =
                    withContext(Dispatchers.IO) { repository.getLatestList() } ?: emptyList()
                _saleProductList.value =
                    withContext(Dispatchers.IO) { repository.getSaleList() } ?: emptyList()
            }.join()
            _dataIsReady.value = true
        }
    }
}