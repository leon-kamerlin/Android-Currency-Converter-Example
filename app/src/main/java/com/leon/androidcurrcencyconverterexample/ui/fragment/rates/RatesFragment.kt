package com.leon.androidcurrcencyconverterexample.ui.fragment.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.leon.androidcurrcencyconverterexample.databinding.FragmentRatesBinding
import com.leon.androidcurrcencyconverterexample.ui.activity.main.MainActivity


class RatesFragment : Fragment() {
    companion object {
        fun newInstance(): RatesFragment {
            return RatesFragment()
        }
    }


    val activity: MainActivity by lazy { context as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRatesBinding = FragmentRatesBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RatesAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        activity.model.rates.observe(activity, {
            adapter.dataSet = it
        })


        activity.model.rates.observe(activity, {
            println(it)
        })


        return binding.root
    }
}