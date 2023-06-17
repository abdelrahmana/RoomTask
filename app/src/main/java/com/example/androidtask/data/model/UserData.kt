package com.example.androidtask.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val userName: String?,
    @ColumnInfo(name = "age") val age: String?,
    @ColumnInfo(name = "job_title") val job: String?,
    val gender: String?

)