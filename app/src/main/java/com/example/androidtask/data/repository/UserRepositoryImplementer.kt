package com.example.androidtask.data.source.remote.repository

import android.content.Context
import com.example.androidtask.R
import com.example.androidtask.data.source.database.AppDataBase
import com.example.androidtask.data.model.UserData

import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImplementer @Inject constructor(val localDataBase: AppDataBase, @ApplicationContext val context : Context) : UserRepository{
    override suspend fun insertUserInfoToDataBase( userData: UserData,completion: (String?, String?) -> Unit) {
       val userDao =  localDataBase.userDao()
        try {
            userDao.insertOneUser(userData)
            completion.invoke(context.getString(R.string.adding_successfully),null)

        }catch (e :Exception) {
            completion.invoke(null,context.getString(R.string.error_happened))
        }
    }

    override suspend fun getUsersInfo(completion: (List<UserData?>?, String?) -> Unit) {
        val userDao =  localDataBase.userDao()
       val userList : List<UserData> =  userDao.getAll()
        completion.invoke(userList,null)
    }


}