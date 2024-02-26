package com.ozdamarsevval.currencyappmvp.model.exchange

data class Result(
    val base: String,
    val data: List<Data>,
    val lastupdate: String
)