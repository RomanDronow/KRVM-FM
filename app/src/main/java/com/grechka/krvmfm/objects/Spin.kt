package com.grechka.krvmfm.objects

import com.google.gson.annotations.*

data class Spin(
    @SerializedName("i")
    val id: String,

    @SerializedName("a")
    val artist: String,

    @SerializedName("s")
    val song: String,

    @SerializedName("r")
    val release: String,

    @SerializedName("t")
    val time: String,

    val img: String
)