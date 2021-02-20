package com.example.penjualan.ui.product

import com.example.penjualan.data.model.product.ResponseCategoryList
import com.example.penjualan.data.model.product.ResponseProductList

interface ProductContract {

    interface Presenter {
        fun getCategory()
        fun getProduct(kd_kategori: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultCategory(responseCategoryList: ResponseCategoryList)
        fun onResultProduct(responseProductList: ResponseProductList)
    }

}