package com.leon.androidcurrcencyconverterexample

import android.app.Application
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.leon.androidcurrcencyconverterexample.currency.CurrencyApi
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class MyApplication : Application() {

    private val applicationModules = module {
        single {
            OkHttpClient.Builder()
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(CurrencyApi.BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        single {
            val retrofit: Retrofit = get()
            retrofit.create(CurrencyApi.Repository::class.java)
        }

        single {
            ReactiveNetwork.observeNetworkConnectivity(applicationContext)
                .filter { it.isAvailable }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


    override fun onCreate() {
        super.onCreate()



        startKoin {
            // Koin Android logger
            androidLogger()
            // Inject Android context
            androidContext(this@MyApplication)
            modules(applicationModules)
        }
    }
}