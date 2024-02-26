package com.ozdamarsevval.currencyappmvp.model.currency

data class BaseResponseCurrency(
    val result: List<Result>,
    val success: Boolean
)