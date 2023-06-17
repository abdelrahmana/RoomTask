package com.example.androidtask.data.source.remote.repository

import com.example.androidtask.data.model.UserData

interface UserRepository {
    suspend fun insertUserInfoToDataBase( userData: UserData,completion: (String?, String?) -> Unit)
    suspend fun getUsersInfo(completion: (List<UserData?>?, String?) -> Unit)

}