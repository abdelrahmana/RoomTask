package com.example.currencyapp.ui.base

import android.location.Location
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtask.util.ProgressDialog

open class BaseViewModel : ViewModel() {
    private val _errorViewModel = MutableLiveData<String?>()
    val errorViewModel :LiveData<String?> = _errorViewModel
    private val _actionOrderMutuable = MutableLiveData<Pair<Int,String>?>()
    val actionOrderLiveData :LiveData<Pair<Int,String>?> = _actionOrderMutuable

    private val _networkLoader = MutableLiveData<ProgressDialog?>()
    val networkLoader :LiveData<ProgressDialog?> = _networkLoader

    private val _responseDataCodeLocation = MutableLiveData<Location?>()
    val responseDataCodeLocation :LiveData<Location?> = _responseDataCodeLocation

    fun setResponseDataLocation(location : Location?) {
        _responseDataCodeLocation.postValue(location)
    }
    fun SetError(error : String?) {
       _errorViewModel.postValue(error)
    }
    fun setOrderAction(pair: Pair<Int,String>? ) {
        _actionOrderMutuable.postValue(pair)
    }
    fun removeActionOrder(activity: FragmentActivity) {
        _actionOrderMutuable.removeObservers(activity)
    }
    fun removeActionOrder(activity: Fragment) {
        _actionOrderMutuable.removeObservers(activity)
    }
    fun setNetworkLoader(loader : ProgressDialog?) {
        _networkLoader.postValue(loader)
    }

}