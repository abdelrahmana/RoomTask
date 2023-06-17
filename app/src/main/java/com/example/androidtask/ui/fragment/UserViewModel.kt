package com.example.androidtask.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.model.UserData
import com.example.androidtask.data.source.remote.repository.UserRepository
import com.example.androidtask.util.DissMissProgress
import com.example.androidtask.util.ShowProgress
import com.example.currencyapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class UserViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    private val _getUserListMutable = MutableLiveData<ArrayList<UserData?>?>()
    val usersLiveData :LiveData<ArrayList<UserData?>?> = _getUserListMutable
    private val _insertUserMutable = MutableLiveData<String?>()
    val insertLiveData :LiveData<String?> = _insertUserMutable

    fun getUsersList(){
        setNetworkLoader(ShowProgress())
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsersInfo { response, errors ->
                response?.let { it ->
                    _getUserListMutable.postValue(it as ArrayList<UserData?>)
                }
                errors?.let { it ->
                    SetError(it)
                }
                setNetworkLoader(DissMissProgress())

            }
        }
    }
    fun postInsertUser(userData: UserData){
        setNetworkLoader(ShowProgress())
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUserInfoToDataBase(userData){ response, errors ->
                setNetworkLoader(DissMissProgress())
                response?.let { it ->
                    _insertUserMutable.postValue(it) // set user message callback
           //         _verifyCode.value = it
                }
                errors?.let { it ->
                    SetError(it)
                }


            }
        }
    }

    fun clearListener(activity : FragmentActivity){
        _getUserListMutable.removeObservers(activity)
        _getUserListMutable.postValue(null)
        errorViewModel.removeObservers(activity)
        SetError(null)
        networkLoader.removeObservers(activity)
        setNetworkLoader(null)

    }
    fun clearListener(fragment : Fragment){
        _getUserListMutable.removeObservers(fragment)
        _getUserListMutable.postValue(null)
        errorViewModel.removeObservers(fragment)
        SetError(null)
        networkLoader.removeObservers(fragment)
        setNetworkLoader(null)

    }
}