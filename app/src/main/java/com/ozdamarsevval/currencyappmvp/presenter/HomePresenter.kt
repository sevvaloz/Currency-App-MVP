package com.ozdamarsevval.currencyappmvp.presenter

import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.model.Model

class HomePresenter(
    private val view: Contract.View
): Contract.Presenter {

    private val model: Contract.Model = Model()

    override fun getAllCurrency() {
        model.requestAllCurrency { allCurrency ->
            view.showAllCurrency(allCurrency)
        }
    }
}