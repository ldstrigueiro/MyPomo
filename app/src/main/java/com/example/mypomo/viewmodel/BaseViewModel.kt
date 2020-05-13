package com.example.mypomo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){
    private val mLoadingUi = MutableLiveData(false)
    val loadingUi: LiveData<Boolean> get() = mLoadingUi
}