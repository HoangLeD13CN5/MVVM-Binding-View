package com.example.samplemvvm.common.di.component

import com.example.samplemvvm.common.di.module.ApiModule
import com.example.samplemvvm.common.di.module.ApplicationModule
import com.example.samplemvvm.common.di.module.DatabaseModule
import com.example.samplemvvm.common.di.module.RepositoryModule
import com.example.samplemvvm.viewModel.RepoListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by HoangLM on 4/3/20.
 */

@Singleton
@Component(modules = [RepositoryModule::class, ApiModule::class, ApplicationModule::class, DatabaseModule::class])
interface AppComponent {
     // when create new viewModel, you should expose this viewModel in here
     // Then you will use it by call this func using appComponent in this Fragment which extend BaseFragment
     fun repoListViewModel(): RepoListViewModel
}