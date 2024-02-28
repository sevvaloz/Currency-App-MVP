package com.ozdamarsevval.currencyappmvp.contract

import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.Data

interface Contract {

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun getAllCurrency()
        fun getExchangeResult(int: Int, base: String, to: String){}
    }

    interface View {
        fun showAllCurrency(allCurrency: MutableList<Result>)
        fun showExchangeResult(allData: MutableList<Data>) {}
        fun showProgressBar() {}
        fun hideProgressBar() {}
    }

}