package com.example.androidtask.data.source.database.dao

import androidx.room.*
import com.example.androidtask.data.model.UserData

@Dao
interface UserDao {
    @Query("SELECT * FROM UserData")
   suspend fun getAll(): List<UserData>

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertOneUser(user: UserData)

    @Delete
    fun delete(user: UserData)
}