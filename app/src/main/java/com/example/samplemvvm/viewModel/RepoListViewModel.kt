package com.example.samplemvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.samplemvvm.base.BaseViewModel
import com.example.samplemvvm.common.extension.addToCompositeDisposable
import com.example.samplemvvm.common.extension.applyNetworkScheduler
import com.example.samplemvvm.entity.Item
import com.example.samplemvvm.viewModel.repos.ReposRepository
import javax.inject.Inject

/**
 * Created by HoangLM on 2020-04-02.
 */
class RepoListViewModel @Inject constructor() : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    @Inject
    lateinit var reposRepository: ReposRepository

    fun fetchRepoList() {
        reposRepository
            .getReposList("trending")
            .applyNetworkScheduler()
            .doOnSubscribe {
                dataLoading.value = true
            }
            .doFinally {
                dataLoading.value = false
            }
            .subscribe({ response ->
                repoListLive.value = response
                empty.value = false
            }, { _ ->
                empty.value = true
            })
            .addToCompositeDisposable(compositeDisposable)
    }
}