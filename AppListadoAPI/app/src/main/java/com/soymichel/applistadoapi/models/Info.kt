package com.soymichel.applistadoapi.models

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
    @SerializedName("pages") val pages: Byte
)