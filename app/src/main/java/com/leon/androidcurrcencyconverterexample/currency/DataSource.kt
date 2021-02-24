package com.leon.androidcurrcencyconverterexample.currency

import com.leon.androidcurrcencyconverterexample.R
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

data class Rates(
    val EUR: Float,
    val AUD: Float,
    val BGN: Float,
    val BRL: Float,
    val CAD: Float,
    val CHF: Float,
    val CNY: Float,
    val CZK: Float,
    val DKK: Float,
    val GBP: Float,
    val HKD: Float,
    val HRK: Float,
    val HUF: Float,
    val IDR: Float,
    val ILS: Float,
    val INR: Float,
    val ISK: Float,
    val JPY: Float,
    val KRW: Float,
    val MXN: Float,
    val MYR: Float,
    val NOK: Float,
    val NZD: Float,
    val PHP: Float,
    val PLN: Float,
    val RON: Float,
    val RUB: Float,
    val SEK: Float,
    val SGD: Float,
    val THB: Float,
    val TRY: Float,
    val USD: Float,
    val ZAR: Float
) {
    fun getCurrencies(): Set<Currency> {
        val set: MutableSet<Currency> = mutableSetOf()
        this::class.declaredMemberProperties.forEach {
            if (it.visibility == KVisibility.PUBLIC) {
                val value: Float = it.getter.call(this) as Float
                if (value != 0.0f) {
                    set.add(
                        Currency(
                            it.name,
                            getIconResource(it.name),
                            value,
                            getNameResource(it.name)
                        )
                    )
                }
            }
        }
        return set
    }

    companion object {
        fun getIconResource(name: String): Int {
            return when (name) {
                "EUR" -> R.drawable.ic_european_union
                "AUD" -> R.drawable.ic_australia
                "BGN" -> R.drawable.ic_bulgaria
                "BRL" -> R.drawable.ic_brazil
                "CAD" -> R.drawable.ic_canada
                "CHF" -> R.drawable.ic_switzerland
                "CNY" -> R.drawable.ic_china
                "CZK" -> R.drawable.ic_czech_republic
                "DKK" -> R.drawable.ic_denmark
                "GBP" -> R.drawable.ic_united_kingdom
                "HKD" -> R.drawable.ic_hong_kong
                "HRK" -> R.drawable.ic_croatia
                "HUF" -> R.drawable.ic_hungary
                "IDR" -> R.drawable.ic_indonesia
                "ILS" -> R.drawable.ic_israel
                "INR" -> R.drawable.ic_india
                "ISK" -> R.drawable.ic_iceland
                "JPY" -> R.drawable.ic_japan
                "KRW" -> R.drawable.ic_south_korea
                "MXN" -> R.drawable.ic_mexico
                "MYR" -> R.drawable.ic_malaysia
                "NOK" -> R.drawable.ic_norway
                "NZD" -> R.drawable.ic_new_zealand
                "PHP" -> R.drawable.ic_philippines
                "PLN" -> R.drawable.ic_poland
                "RON" -> R.drawable.ic_romania
                "RUB" -> R.drawable.ic_russia
                "SEK" -> R.drawable.ic_sweden
                "SGD" -> R.drawable.ic_singapore
                "THB" -> R.drawable.ic_thailand
                "TRY" -> R.drawable.ic_turkey
                "USD" -> R.drawable.ic_united_states_of_america
                "ZAR" -> R.drawable.ic_south_africa

                else -> R.drawable.ic_south_africa
            }
        }

        fun getNameResource(name: String): Int {
            return when (name) {
                "EUR" -> R.string.eur
                "AUD" -> R.string.aud
                "BGN" -> R.string.bgn
                "BRL" -> R.string.brl
                "CAD" -> R.string.cad
                "CHF" -> R.string.chf
                "CNY" -> R.string.cny
                "CZK" -> R.string.czk
                "DKK" -> R.string.dkk
                "GBP" -> R.string.gbp
                "HKD" -> R.string.hkd
                "HRK" -> R.string.hrk
                "HUF" -> R.string.huf
                "IDR" -> R.string.idr
                "ILS" -> R.string.ils
                "INR" -> R.string.inr
                "ISK" -> R.string.isk
                "JPY" -> R.string.jpy
                "KRW" -> R.string.krw
                "MXN" -> R.string.mxn
                "MYR" -> R.string.myr
                "NOK" -> R.string.nok
                "NZD" -> R.string.nzd
                "PHP" -> R.string.php
                "PLN" -> R.string.pln
                "RON" -> R.string.ron
                "RUB" -> R.string.rub
                "SEK" -> R.string.sek
                "SGD" -> R.string.sgd
                "THB" -> R.string.thb
                "TRY" -> R.string.turkish_lira
                "USD" -> R.string.usd
                "ZAR" -> R.string.zar

                else -> R.string.zar
            }
        }
    }
}

data class DataSource(val base: String, val date: String, val rates: Rates)