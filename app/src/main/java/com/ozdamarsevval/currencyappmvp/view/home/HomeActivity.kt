package com.ozdamarsevval.currencyappmvp.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.databinding.ActivityHomeBinding
import com.ozdamarsevval.currencyappmvp.presenter.HomePresenter
import com.ozdamarsevval.currencyappmvp.view.currencydetail.CurrencyDetailActivity
import com.ozdamarsevval.currencyappmvp.model.currency.Result

class HomeActivity : AppCompatActivity(), Contract.View {

    private lateinit var binding: ActivityHomeBinding
    private var homePresenter = HomePresenter(this)
    private val currencyAdapter = CurrencyAdapter(onCurrencyClick = ::onCurrencyClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvCurrency.adapter = currencyAdapter
        }
        homePresenter.getAllCurrency()
    }

    override fun showAllCurrency(allCurrency: List<Result>) {
        currencyAdapter.submitList(allCurrency)
        Log.d("CURRENCY", "All Currency: $allCurrency")
    }

    private fun onCurrencyClick(currency: Result) {
        val intent = Intent(this, CurrencyDetailActivity::class.java)
        intent.putExtra("currency", currency)
        startActivity(intent)
    }

}