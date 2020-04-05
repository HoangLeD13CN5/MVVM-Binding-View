package com.example.samplemvvm.viewModel.repos

import com.example.samplemvvm.entity.Item
import io.reactivex.Single

/**
 * Created by HoangLM on 4/3/20.
 */
interface ReposRepository {
    fun getReposList(search: String): Single<List<Item>>
}