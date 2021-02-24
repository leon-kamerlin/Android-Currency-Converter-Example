package com.leon.androidcurrcencyconverterexample.ui.fragment.rates

import androidx.recyclerview.widget.RecyclerView
import com.leon.androidcurrcencyconverterexample.currency.Currency
import com.leon.androidcurrcencyconverterexample.databinding.ItemCurrencyRatesBinding

class RatesViewHolder(private val binding: ItemCurrencyRatesBinding) : RecyclerView.ViewHolder(binding.root) {

    private fun rateFormat(number: Float): String {
        return String.format("%10.2f", number)
    }

    fun bindTo(currency: Currency, position: Int) {

    }
}