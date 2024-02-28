package com.ozdamarsevval.currencyappmvp.presenter

import android.os.Handler
import android.os.Looper
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.repository.CurrencyRepository
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyDetailPresenter @Inject constructor(private val currencyRepository: CurrencyRepository) : Contract.Presenter {

    private var view: Contract.View? = null
    private val handler = Handler(Looper.getMainLooper())
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

    override fun getExchangeResult(int: Int, base: String, to: String) {
        CoroutineScope(Dispatchers.IO).launch {
            handler.post { view?.showProgressBar() }
            currencyRepository.requestExchangeCurrency(object :
                CurrencyRepository.GetExchangeListener {
                override fun setExchange(allData: MutableList<Data>) {
                    view?.showExchangeResult(allData)
                    handler.post { view?.hideProgressBar() }
                }
            }, int, base, to)
        }
    }

}