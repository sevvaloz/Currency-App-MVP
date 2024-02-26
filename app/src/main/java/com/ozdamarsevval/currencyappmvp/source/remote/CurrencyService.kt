package com.ozdamarsevval.currencyappmvp.source.remote

import com.ozdamarsevval.currencyappmvp.model.currency.BaseResponseCurrency
import com.ozdamarsevval.currencyappmvp.model.exchange.BaseResponseExchange
import com.ozdamarsevval.currencyappmvp.util.Constant.CURRENCY_API
import com.ozdamarsevval.currencyappmvp.util.Constant.CONTENT_TYPE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyService {
    @Headers("content-type: $CONTENT_TYPE", "authorization: $CURRENCY_API")
    @GET("economy/symbols")
    fun getAllCurrency(): Call<BaseResponseCurrency>?

    @Headers("content-type: $CONTENT_TYPE", "authorization: $CURRENCY_API")
    @GET("economy/exchange")
    fun getExchangeCurrency(
        @Query("int") int: Int,
        @Query("base") base: String,
        @Query("to") to: String)
    : Call<BaseResponseExchange>?
}