package com.example.samplemvvm.common.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by HoangLM on 4/3/20.
 */
object AppExecutor {

    private val networkExecutor: Executor
    private val localExecutor: Executor

    init {
        networkExecutor = Executors.newFixedThreadPool(3)
        localExecutor = Executors.newSingleThreadExecutor()
    }

    fun networkExecutor(): Scheduler {
        return Schedulers.from(networkExecutor)
    }

    fun localExecutor(): Scheduler {
        return Schedulers.from(localExecutor)
    }
}