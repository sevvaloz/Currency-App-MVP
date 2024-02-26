package com.ozdamarsevval.currencyappmvp.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object CurrencyInstance {
    val service: CurrencyService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.collectapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(CurrencyService::class.java)
    }
}