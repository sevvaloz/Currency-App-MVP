package com.ozdamarsevval.currencyappmvp.repository

import android.util.Log
import com.ozdamarsevval.currencyappmvp.model.currency.BaseResponseCurrency
import com.ozdamarsevval.currencyappmvp.source.remote.CurrencyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.BaseResponseExchange
import com.ozdamarsevval.currencyappmvp.model.exchange.Data

class CurrencyRepository @Inject constructor(
    private val currencyService: CurrencyService) {

    interface GetCurrencyListener {
        fun setCurrency(allCurrency: MutableList<Result>)
    }

    interface GetExchangeListener {
        fun setExchange(allData: MutableList<Data>)
    }

    fun requestAllCurrency(listener : GetCurrencyListener) {
        currencyService.getAllCurrency()?.enqueue(object : Callback<BaseResponseCurrency> {
            override fun onResponse(call: Call<BaseResponseCurrency>, response: Response<BaseResponseCurrency>) {
                if (response.code() == 200 && response.body() != null) {
                    listener.setCurrency((response.body()!!.result.toMutableList()))
                } else {
                    Log.d("CURRENCY", "Empty Body: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<BaseResponseCurrency>, t: Throwable) {
                Log.d("CURRENCY", "Fail: ${t.message}")
            }
        })
    }

    fun requestExchangeCurrency(listener: GetExchangeListener, int: Int, base: String, to: String) {
        currencyService.getExchangeCurrency(int, base, to)?.enqueue(object : Callback<BaseResponseExchange> {
            override fun onResponse(call: Call<BaseResponseExchange>, response: Response<BaseResponseExchange>) {
                if (response.code() == 200 && response.body() != null) {
                    listener.setExchange(response.body()!!.result.data.toMutableList())
                } else {
                    Log.d("EXCHANGE", "Empty Body: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<BaseResponseExchange>, t: Throwable) {
                Log.d("EXCHANGE", "Fail: ${t.message}")
            }
        })
    }
}