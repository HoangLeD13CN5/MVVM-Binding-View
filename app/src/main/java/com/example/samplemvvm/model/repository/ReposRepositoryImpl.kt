package com.example.samplemvvm.model.repository

import com.example.samplemvvm.entity.Item
import com.example.samplemvvm.model.api.ReposApi
import com.example.samplemvvm.model.local.database.dao.ReposDao
import com.example.samplemvvm.model.local.database.entitydb.DataMapper
import com.example.samplemvvm.model.local.database.entitydb.ItemDbEntity
import com.example.samplemvvm.model.local.database.entitydb.OwnerDbEntity
import com.example.samplemvvm.viewModel.repos.ReposRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by HoangLM on 4/3/20.
 */
class ReposRepositoryImpl @Inject constructor() : ReposRepository {

    @Inject
    lateinit var reposApi: ReposApi

    @Inject
    lateinit var reposDao: ReposDao

    private fun insertRepos(item: Item): Observable<Item> {
        val owner = OwnerDbEntity(item.owner.id,item.id, item.owner.avatar_url)
        val dbItem = ItemDbEntity(
            item.id,
            item.full_name,
            item.description,
            item.forks_count,
            item.stargazers_count,
            item.html_url,
            item.open_issues_count,
            owner
        )
        return reposDao
            .insert(dbItem)
            .map {
                return@map item
            }.toObservable()
    }

    override fun getReposList(search: String): Single<List<Item>> {
        return reposApi
            .getListRepos(search)
            .map { listRepos ->
                return@map listRepos.items
            }
            .flatMap { listRepos ->
                return@flatMap reposDao
                    .deleteAll()
                    .map {
                        return@map listRepos
                    }
            }
            .flattenAsObservable { messages ->
                messages // chuyển ArrayList -> Iterator
            }
            .flatMap { item ->
                // tách từng message để xử lí
                return@flatMap insertRepos(item)
            }.toList()
            .onErrorResumeNext {
                return@onErrorResumeNext reposDao
                    .getAll()
                    .map {
                        return@map it.map {
                            return@map DataMapper.convertItemDbToEntity(it)
                        }
                    }
            }
    }

}