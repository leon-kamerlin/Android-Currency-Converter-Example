package com.leon.androidcurrcencyconverterexample.ui.fragment

import androidx.recyclerview.widget.DiffUtil
import com.leon.androidcurrcencyconverterexample.currency.Currency

class CurrencyDiffCallback(var newPersons: List<Currency>, var oldPersons: List<Currency>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPersons.size
    }

    override fun getNewListSize(): Int {
        return newPersons.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].code === newPersons[newItemPosition].code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersons[oldItemPosition].value == newPersons[newItemPosition].value
    }

}