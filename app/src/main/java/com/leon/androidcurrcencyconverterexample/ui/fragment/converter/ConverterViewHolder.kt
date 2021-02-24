package com.leon.androidcurrcencyconverterexample.ui.fragment.converter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leon.androidcurrcencyconverterexample.currency.Currency
import com.leon.androidcurrcencyconverterexample.databinding.ItemCurrencyConverterBinding

class ConverterViewHolder(val binding: ItemCurrencyConverterBinding) : RecyclerView.ViewHolder(binding.root) {

    private fun rateFormat(number: Float): String {
        return String.format("%10.2f", number)
    }

    fun bindTo(currency: Currency, position: Int, baseValue: Float = 1f) {
        with(binding) {
            currencyCode.text = currency.code
            currencyName.setText(currency.nameResource)
            editText.setText(rateFormat(currency.value * baseValue))
            Glide
                .with(binding.root.context)
                .load(currency.flagResource)
                .into(flag)

            if (position == 0) {
                editText.requestFocus()
            }


            /*if (position == 0) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                        val value = charSequence.toString().replace("\\s+".toRegex(), "")
                        val num: Float
                        if (value.isEmpty()) {
                            editText.setText("0")
                        } else {
                            val s2 = value.replace(",", ".")
                            num = try {
                                java.lang.Float.valueOf(s2)
                            } catch (e: Exception) {
                                0f
                            }


                            baseValue = num

                        }
                    }

                    override fun afterTextChanged(editable: Editable) {
                    }
                })
            }*/
        }
    }
}