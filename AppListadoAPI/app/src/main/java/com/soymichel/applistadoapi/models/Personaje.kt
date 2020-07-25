package com.soymichel.applistadoapi.models

import com.google.gson.annotations.SerializedName

data class Personaje(
    @SerializedName("name") val name: String,
    @SerializedName("species") val caption: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("gender") val gender: String
)