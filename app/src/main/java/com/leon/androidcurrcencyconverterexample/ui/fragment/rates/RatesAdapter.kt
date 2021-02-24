package com.leon.androidcurrcencyconverterexample.ui.fragment.rates

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leon.androidcurrcencyconverterexample.currency.Currency
import com.leon.androidcurrcencyconverterexample.databinding.ItemCurrencyRatesBinding
import com.leon.androidcurrcencyconverterexample.ui.fragment.CurrencyDiffCallback

class RatesAdapter(private val context: Context) : RecyclerView.Adapter<RatesViewHolder>() {
    var dataSet: Set<Currency> = setOf()
        set(value) {
            if (dataSet.isEmpty()) {
                field = value
                notifyDataSetChanged()
            } else {
                updateList(dataSet.toList(), value.toList())
                field = value;
            }

        }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCurrencyRatesBinding.inflate(inflater, viewGroup, false)

        return RatesViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RatesViewHolder, position: Int) {
        val item = dataSet.toList()[position]
        viewHolder.bindTo(item, position)

    }

    override fun getItemCount() = dataSet.size

    private fun updateList(oldList: List<Currency>, newList: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }
}