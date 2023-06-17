package com.example.androidtask.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtask.data.source.database.dao.UserDao
import com.example.androidtask.data.model.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}