package com.leon.androidcurrcencyconverterexample.ui.activity.main

import android.annotation.SuppressLint
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.leon.androidcurrcencyconverterexample.currency.Currency
import com.leon.androidcurrcencyconverterexample.currency.CurrencyApi
import com.leon.androidcurrcencyconverterexample.currency.DataSource
import com.leon.androidcurrcencyconverterexample.currency.Rates
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class MainViewModel(
    private val repository: CurrencyApi.Repository,
    private val connectivity: Observable<Connectivity>
) : ViewModel() {


    companion object {
        fun parseRatesValue(rates: Rates, key: String): Currency? {
            return null
        }
    }

    init {
        repository.getRates()
            .repeatWhen { completed -> completed.delay(1, TimeUnit.SECONDS) }
            .retryWhen { connectivity }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { _dataSource.value = it }
    }

    private val _dataSource = MutableLiveData<DataSource>()
    val dataSource: LiveData<DataSource> = _dataSource

    val rates: LiveData<Set<Currency>> =
        Transformations.map(dataSource, Function {
            val set: MutableSet<Currency> = mutableSetOf()
            set.add(Currency(it.base, Rates.getIconResource(it.base), 1f, Rates.getNameResource(it.base)))
            set.addAll(it.rates.getCurrencies().toMutableSet())
            return@Function set
        })
}