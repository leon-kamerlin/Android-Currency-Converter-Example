package com.leon.androidcurrcencyconverterexample.currency

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

class CurrencyApi {

    companion object {
        const val BASE_URL = "https://revolut.duckdns.org/"

        //url paths
        const val LATEST = "latest"
        const val BASE_CURRENCY = "base"
    }

    interface Repository {
        @GET(LATEST)
        fun getRates(@Query(BASE_CURRENCY) baseCurrency: String = "eur"): Observable<DataSource>
    }
}