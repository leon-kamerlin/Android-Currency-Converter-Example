package com.leon.androidcurrcencyconverterexample.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.leon.androidcurrcencyconverterexample.R
import com.leon.androidcurrcencyconverterexample.currency.CurrencyApi
import com.leon.androidcurrcencyconverterexample.databinding.ActivityMainBinding
import com.leon.androidcurrcencyconverterexample.ui.fragment.converter.ConverterFragment
import com.leon.androidcurrcencyconverterexample.ui.fragment.rates.RatesFragment
import io.reactivex.Observable
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: CurrencyApi.Repository by inject()
    private val connectivity: Observable<Connectivity> by inject()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val model: MainViewModel by viewModels {
        MainViewModelFactory(repository, connectivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        println(binding.viewPagerBottom)
        toolbarSetup()
        setupViewPager(binding.viewPagerBottom, binding.tabs)
    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true) // enable overriding the default toolbar layout
        supportActionBar?.setDisplayShowTitleEnabled(false) // disable the default ti
    }


    private fun setupViewPager(viewPager: ViewPager2, tabLayout: TabLayout) {
        val adapter = ViewPagerAdapterHelper(
            supportFragmentManager,
            lifecycle
        )
        adapter.fragmentList.add(RatesFragment.newInstance())
        adapter.fragmentList.add(ConverterFragment.newInstance())
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager, true
        ) { tab: TabLayout.Tab, position: Int ->
            println(position)
            when (position) {
                0 -> tab.setText(R.string.all_rates)
                1 -> tab.setText(R.string.converter)
            }
        }
        tabLayoutMediator.attach()
    }
}