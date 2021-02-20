package com.example.penjualan.data.model.product

import com.google.gson.annotations.SerializedName

data class ResponseCategoryList(

    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val dataCategory: List<DataCategory>

)
