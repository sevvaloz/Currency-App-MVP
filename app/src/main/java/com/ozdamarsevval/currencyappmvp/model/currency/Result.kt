package com.ozdamarsevval.currencyappmvp.model.currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val code: String,
    val name: String
): Parcelable