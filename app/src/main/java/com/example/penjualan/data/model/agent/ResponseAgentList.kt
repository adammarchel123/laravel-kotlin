package com.example.penjualan.data.model.agent

import com.google.gson.annotations.SerializedName

data class ResponseAgentList(

    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val dataAgent: List<DataAgent>
)