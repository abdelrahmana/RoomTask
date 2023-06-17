package com.example.androidtask.di

import android.content.Context
import androidx.room.Room
import com.example.androidtask.data.source.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class, ActivityComponent::class,
    ServiceComponent::class)
class DaoDi {
    @Provides
    fun getImageDao(@ApplicationContext context: Context): AppDataBase {
       return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "my_database"
        ).build()
    }
}