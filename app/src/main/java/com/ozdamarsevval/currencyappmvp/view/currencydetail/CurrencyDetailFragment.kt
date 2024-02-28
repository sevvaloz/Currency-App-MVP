package com.ozdamarsevval.currencyappmvp.view.currencydetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.databinding.FragmentCurrencyDetailBinding
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.Data
import com.ozdamarsevval.currencyappmvp.presenter.CurrencyDetailPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment(), Contract.View {

    @Inject
    lateinit var currencyDetailPresenter: CurrencyDetailPresenter
    private lateinit var binding: FragmentCurrencyDetailBinding
    private var allCurrencyCode = mutableListOf<String>()
    private var selectedExchangeCurrencyCode: String = ""
    private var selectedExchangeCurrencyQuantity: Int = 0
    private var currency: Result? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        currencyDetailPresenter.attachView(this)

        currency = arguments?.getParcelable<Result>("currency")

        binding.apply {
            if(currency != null){
                tvCurrencyName.text = "${currency?.name} (${currency?.code})"

                spnrCurrencyBase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectedExchangeCurrencyCode = parent?.getItemAtPosition(position).toString()
                        Log.d("EXCHANGE", "Selected value: $selectedExchangeCurrencyCode")
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Log.d("EXCHANGE", "Selected value: null")
                    }
                }

                btnExchange.setOnClickListener {
                    selectedExchangeCurrencyQuantity = if(!etCurrencyQuantity.text.isNullOrEmpty()){
                        etCurrencyQuantity.text.toString().toInt()
                    } else {
                        1
                    }
                    currencyDetailPresenter.getExchangeResult(
                        int = selectedExchangeCurrencyQuantity,
                        base = currency!!.code,
                        to = selectedExchangeCurrencyCode
                    )
                }
            }
        }
        //currencyDetailPresenter.getAllCurrency()

        return binding.root
    }

    override fun showAllCurrency(allCurrency: MutableList<Result>) {
        binding.apply {
            allCurrency.forEach { currency ->
                allCurrencyCode.add(currency.code)
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, allCurrencyCode)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnrCurrencyBase.adapter = adapter
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showExchangeResult(allData: MutableList<Data>) {
        super.showExchangeResult(allData)
        Log.d("EXCHANGE", "All Data: $allData")
        binding.apply {
            allData.forEach { data ->
                tvExchangeResult.text = "$selectedExchangeCurrencyQuantity ${currency?.code} = ${data.calculated.toString()} ${selectedExchangeCurrencyCode}"
            }
        }
    }
    override fun showProgressBar() {
        super.showProgressBar()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        super.hideProgressBar()
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        currencyDetailPresenter.getAllCurrency()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currencyDetailPresenter.detachView()
    }

}