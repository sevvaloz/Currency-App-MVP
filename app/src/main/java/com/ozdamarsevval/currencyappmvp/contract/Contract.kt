package com.ozdamarsevval.currencyappmvp.contract

import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.Data

interface Contract {

    interface Model {
        fun requestAllCurrency(callback: (List<Result>) -> Unit)
        fun requestExchangeCurrency(int: Int, base: String, to: String, callback: (List<Data>) -> Unit) {}
    }

    interface Presenter {
        fun getAllCurrency()
        fun getExchangeResult(int: Int, base: String, to: String){}
    }

    interface View {
        fun showAllCurrency(allCurrency: List<Result>)
        fun showExchangeResult(allData: List<Data>) {}
    }

}