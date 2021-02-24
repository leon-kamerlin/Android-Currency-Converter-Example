package com.leon.androidcurrcencyconverterexample.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.leon.androidcurrcencyconverterexample.currency.CurrencyApi
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    private val repository: CurrencyApi.Repository,
    private val connectivity: Observable<Connectivity>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, connectivity) as T
        }
        throw IllegalArgumentException()
    }
}