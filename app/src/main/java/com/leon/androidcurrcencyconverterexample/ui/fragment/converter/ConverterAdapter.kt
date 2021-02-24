package com.leon.androidcurrcencyconverterexample.ui.fragment.converter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leon.androidcurrcencyconverterexample.currency.Currency
import com.leon.androidcurrcencyconverterexample.databinding.ItemCurrencyConverterBinding
import com.leon.androidcurrcencyconverterexample.ui.fragment.CurrencyDiffCallback


class ConverterAdapter(private val context: Context) :
    RecyclerView.Adapter<ConverterViewHolder>() {
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

    var baseValue: Float = 1f
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConverterViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCurrencyConverterBinding.inflate(inflater, viewGroup, false)
        return ConverterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ConverterViewHolder, position: Int) {
        val item = dataSet.toList()[position]
        viewHolder.bindTo(item, position, baseValue)
    }

    private fun updateList(oldList: List<Currency>, newList: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }




    override fun getItemCount() = dataSet.size
}