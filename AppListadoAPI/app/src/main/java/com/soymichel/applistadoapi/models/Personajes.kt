package com.soymichel.applistadoapi.models

import com.google.gson.annotations.SerializedName

data class Personajes(
    @SerializedName("results") val results: ArrayList<Personaje>,
    @SerializedName("info") val info: Info
)