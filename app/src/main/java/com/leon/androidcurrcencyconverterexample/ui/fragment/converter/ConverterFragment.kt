package com.leon.androidcurrcencyconverterexample.ui.fragment.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.leon.androidcurrcencyconverterexample.databinding.FragmentConverterBinding
import com.leon.androidcurrcencyconverterexample.ui.activity.main.MainActivity


class ConverterFragment : Fragment() {
    companion object {
        fun newInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }

    val activity: MainActivity by lazy { context as MainActivity }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentConverterBinding = FragmentConverterBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ConverterAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        activity.model.rates.observe(activity, {
            adapter.dataSet = it
        })

        return binding.root
    }
}