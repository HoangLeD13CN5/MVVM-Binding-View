package com.example.samplemvvm.common.extension

import com.example.samplemvvm.common.executor.AppExecutor
import com.example.samplemvvm.common.executor.IntervalGenerator
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by HoangLM on 4/3/20.
 */

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> Observable<T>.applyNetworkScheduler(): Observable<T> {
    return this
        .subscribeOn(AppExecutor.networkExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyLocalScheduler(): Observable<T> {
    return this
        .subscribeOn(AppExecutor.localExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyNetworkScheduler(): Single<T> {
    return this
        .subscribeOn(AppExecutor.networkExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyLocalScheduler(): Single<T> {
    return this
        .subscribeOn(AppExecutor.localExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyNetworkScheduler(): Completable {
    return this
        .subscribeOn(AppExecutor.networkExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applyLocalScheduler(): Completable {
    return this
        .subscribeOn(AppExecutor.localExecutor())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.retryInterval(maxRetry: Int): Observable<T> {
    return this.retryWhen(RetryWithDelay(maxRetry))
}

fun <T> Observable<T>.retryInterval(maxRetry: Int, timeInterval: Long): Observable<T> {
    return this.retryWhen(RetryWithDelay(maxRetry, timeInterval, false))
}

fun <T> schedulersFlowableTransformer(): FlowableTransformer<T, T> {
    return FlowableTransformer {
        it.unsubscribeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun applySchedulers(): CompletableTransformer {
    return CompletableTransformer { upstream ->
        upstream
            .unsubscribeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> ObservableEmitter<T>.checkDisposed(): ObservableEmitter<T>? {
    return if (this.isDisposed) {
        null
    } else {
        this
    }
}

private class RetryWithDelay : Function<Observable<Throwable>, Observable<*>> {

    private val interval: IntervalGenerator
    private val maxRetries: Int
    private var retryCount: Int
    private var isRandomInterval: Boolean

    constructor(maxRetries: Int) {
        this.maxRetries = maxRetries
        this.retryCount = 0
        this.interval = IntervalGenerator()
        this.isRandomInterval = true
    }

    constructor(maxRetries: Int, timeInterval: Long, isRandomInterval: Boolean) {
        this.maxRetries = maxRetries
        this.retryCount = 0
        this.interval = IntervalGenerator(timeInterval)
        this.isRandomInterval = isRandomInterval
    }

    override fun apply(attempts: Observable<Throwable>): Observable<*> {
        return attempts.flatMap(object : Function<Throwable, Observable<*>> {
            override fun apply(throwable: Throwable): Observable<*> {
                if (++retryCount <= maxRetries) {
                    return Observable.timer(interval.next(isRandom = isRandomInterval), TimeUnit.MILLISECONDS)
                }
                return Observable.error<Throwable>(throwable)
            }

        })
    }
}