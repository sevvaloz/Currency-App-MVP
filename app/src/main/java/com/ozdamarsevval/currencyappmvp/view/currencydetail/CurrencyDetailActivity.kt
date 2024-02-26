package com.ozdamarsevval.currencyappmvp.view.currencydetail

import android.R
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.databinding.ActivityCurrencyDetailBinding
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.Data
import com.ozdamarsevval.currencyappmvp.presenter.CurrencyDetailPresenter

class CurrencyDetailActivity : AppCompatActivity(), Contract.View {

    private lateinit var binding: ActivityCurrencyDetailBinding
    private var currencyDetailPresenter = CurrencyDetailPresenter(this)
    private var allCurrencyCode = mutableListOf<String>()
    private var selectedValue: String = ""

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currency = intent.getParcelableExtra("currency", Result::class.java)

        binding.apply {
            if(currency != null){
                tvCurrencyName.text = "${currency.name} (${currency.code})"

                spnrCurrencyBase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedValue = parent?.getItemAtPosition(position).toString()
                        Log.d("EXCHANGE", "Selected value: $selectedValue")
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Log.d("EXCHANGE", "Selected value: null")
                    }
                }

                btnExchange.setOnClickListener {
                    val quantity: Int = if(!etCurrencyQuantity.text.isNullOrEmpty()){
                        etCurrencyQuantity.text.toString().toInt()
                    } else {
                        1
                    }
                    Log.d("EXCHANGE", "Selected value: $quantity")
                    currencyDetailPresenter.getExchangeResult(
                        int = quantity,
                        base = currency.code,
                        to = selectedValue
                    )
                }
            }
        }
        currencyDetailPresenter.getAllCurrency()
    }

    override fun showAllCurrency(allCurrency: List<Result>) {
        binding.apply {
            allCurrency.forEach { currency ->
                allCurrencyCode.add(currency.code)
            }
            val adapter = ArrayAdapter(applicationContext, R.layout.simple_spinner_item, allCurrencyCode)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spnrCurrencyBase.adapter = adapter
        }
    }

    override fun showExchangeResult(allData: List<Data>) {
        super.showExchangeResult(allData)
        Log.d("EXCHANGE", "All Data: $allData")
        binding.apply {
            allData.forEach { data ->
                tvExchangeResult.text = data.calculated.toString()
            }
        }

    }
}