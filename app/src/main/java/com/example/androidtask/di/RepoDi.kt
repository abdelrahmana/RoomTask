package com.example.androidtask.di

import android.content.Context
import com.example.androidtask.data.source.database.AppDataBase
import com.example.androidtask.data.source.remote.repository.UserRepository
import com.example.androidtask.data.source.remote.repository.UserRepositoryImplementer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class, FragmentComponent::class,
    ActivityComponent::class)
class RepoDi {
    @Provides
    fun getRepo(
        localDataBase: AppDataBase,@ApplicationContext context : Context
    ): UserRepository {
        return  UserRepositoryImplementer(localDataBase,context)
    }


}