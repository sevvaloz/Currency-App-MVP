package com.ozdamarsevval.currencyappmvp.presenter

import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.repository.CurrencyRepository
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(private val currencyRepository: CurrencyRepository): Contract.Presenter {

    private var view: Contract.View? = null

    override fun attachView(view: Contract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getAllCurrency() {
        CoroutineScope(Dispatchers.IO).launch {
            currencyRepository.requestAllCurrency(object :
                CurrencyRepository.GetCurrencyListener {
                override fun setCurrency(allCurrency: MutableList<Result>) {
                    view?.showAllCurrency(allCurrency)
                }
            })
        }
    }

}