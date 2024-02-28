package com.ozdamarsevval.currencyappmvp.model

import android.util.Log
import com.ozdamarsevval.currencyappmvp.contract.Contract
import com.ozdamarsevval.currencyappmvp.model.currency.BaseResponseCurrency
import com.ozdamarsevval.currencyappmvp.model.currency.Result
import com.ozdamarsevval.currencyappmvp.model.exchange.BaseResponseExchange
import com.ozdamarsevval.currencyappmvp.model.exchange.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
class Model : Contract.Model {

    override fun requestAllCurrency(callback: (List<Result>) -> Unit) {
        CurrencyInstance.service.getAllCurrency()?.enqueue(object : Callback<BaseResponseCurrency> {
            override fun onResponse(call: Call<BaseResponseCurrency>, response: Response<BaseResponseCurrency>) {
                val _response = response.body()
                if (_response?.success == true && _response.result != null) {
                    callback(_response.result)
                } else {
                    Log.d("CURRENCY", "Empty Body: ${response.code()}")
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<BaseResponseCurrency>, t: Throwable) {
                Log.d("CURRENCY", "Fail: ${t.message}")
                callback(emptyList())
            }
        })
    }

    override fun requestExchangeCurrency(int: Int, base: String, to: String, callback: (List<Data>) -> Unit) {
        super.requestExchangeCurrency(int, base, to, callback)
        CurrencyInstance.service.getExchangeCurrency(int, base, to)?.enqueue(object : Callback<BaseResponseExchange> {
            override fun onResponse(call: Call<BaseResponseExchange>, response: Response<BaseResponseExchange>) {
                val _response = response.body()
                Log.d("EXCHANGE", "Response: ${response.body()}")
                if (_response?.success == true) {
                    callback(_response.result.data)
                } else {
                    Log.d("EXCHANGE", "Empty Body: ${response.code()}")
                    callback(emptyList())
                }
            }

            override fun onFailure(call: Call<BaseResponseExchange>, t: Throwable) {
                Log.d("EXCHANGE", "Fail: ${t.message}")
                callback(emptyList())
            }
        })
    }

}*/
