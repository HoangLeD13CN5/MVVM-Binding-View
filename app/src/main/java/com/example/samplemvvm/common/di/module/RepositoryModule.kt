package com.example.samplemvvm.common.di.module

import com.example.samplemvvm.model.repository.ReposRepositoryImpl
import com.example.samplemvvm.viewModel.repos.ReposRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HoangLM on 4/3/20.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideReposRepos(reposRepositoryImpl: ReposRepositoryImpl): ReposRepository {
        return reposRepositoryImpl
    }
}