package com.ozdamarsevval.currencyappmvp.presenter

import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.model.Model

class CurrencyDetailPresenter(
    private val view: Contract.View
): Contract.Presenter {

    private val model: Contract.Model = Model()

    override fun getAllCurrency() {
        model.requestAllCurrency { allCurrency ->
            view.showAllCurrency(allCurrency)
        }
    }

    override fun getExchangeResult(int: Int, base: String, to: String) {
        super.getExchangeResult(int, base, to)
        model.requestExchangeCurrency(int, base, to){ allData ->
            view.showExchangeResult(allData)
        }
    }
}