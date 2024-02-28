package com.ozdamarsevval.currencyappmvp.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ozdamarsevval.currencyappmvp.R
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.databinding.FragmentHomeBinding
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.presenter.HomePresenter
import com.ozdamarsevval.currencyappmvp.view.currencydetail.CurrencyDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), Contract.View {

    @Inject
    lateinit var homePresenter: HomePresenter
    private lateinit var binding: FragmentHomeBinding
    private val currencyAdapter = CurrencyAdapter(onCurrencyClick = ::onCurrencyClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homePresenter.attachView(this)

        binding.apply {
            rvCurrency.adapter = currencyAdapter
        }
        //homePresenter.getAllCurrency()

        return binding.root
    }

    override fun showAllCurrency(allCurrency: MutableList<Result>) {
        currencyAdapter.submitList(allCurrency)
        Log.d("CURRENCY", "All Currency: $allCurrency")
    }

    private fun onCurrencyClick(currency: Result) {
        val bundle = Bundle().apply {
            putParcelable("currency", currency)
        }
        val currencyDetailFragment = CurrencyDetailFragment()
        currencyDetailFragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintlayout, currencyDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onResume() {
        super.onResume()
        homePresenter.getAllCurrency()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homePresenter.detachView()
    }

}