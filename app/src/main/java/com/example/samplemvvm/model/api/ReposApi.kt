package com.example.samplemvvm.model.api

import com.example.samplemvvm.entity.GitResponse
import com.example.samplemvvm.model.api.request.AddRepositoryRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by HoangLM on 4/3/20.
 */
interface ReposApi {
    @GET("search/repositories")
    fun getListRepos(@Query("q") search:String, @Query("sort") sort: String = "stars"): Single<GitResponse>

    @POST("repository")
    fun addRepository(@Body request: AddRepositoryRequest): Single<Unit>
}