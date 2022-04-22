package com.example.worldnews.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worldnews.api.ApiFactory
import com.example.worldnews.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WorldNewsViewModel(application: Application) : BaseViewModel<ProcessUiEvent>() {

    private val compositeDisposable = CompositeDisposable()
    private val db = AppDatabase.getInstance(application)
    val worldNewsList = db.worldNewsInfoDao().getWorldNewsList()
    private var loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun processUiEvent(event: ProcessUiEvent) {
        when (event) {
            is ProcessUiEvent.InitTypeOfNews -> {
                if (event.type == TypeOfNews.AMERICAN) loadAmericanNews() else loadRussianNews()
            }
        }
    }

    private fun loadRussianNews() {
        val disposable = ApiFactory.apiService.getRussianNews()
            .doOnSubscribe { loaderLiveData.postValue(true) }
            .doFinally { loaderLiveData.postValue(false) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.worldNewsInfoDao().clearedWorldNewsList()
                db.worldNewsInfoDao().insertWorldNewsList(it.collection)
                Log.d("TEST", "Success: $it")

            }, {
                Log.d("TEST", "Failure: " + it.message)

            })
        compositeDisposable.add(disposable)
    }

    private fun loadAmericanNews() {
        val disposable = ApiFactory.apiService.getAmericanNews()
            .doOnSubscribe { loaderLiveData.postValue(true) }
            .doFinally { loaderLiveData.postValue(false) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.worldNewsInfoDao().clearedWorldNewsList()
                db.worldNewsInfoDao().insertWorldNewsList(it.collection)
                Log.d("TEST", "Success: $it")

            }, {
                Log.d("TEST", "Failure: " + it.message)

            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun peekProgressLiveData(): LiveData<Boolean> = loaderLiveData
}