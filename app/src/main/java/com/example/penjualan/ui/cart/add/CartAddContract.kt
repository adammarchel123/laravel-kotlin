package com.example.penjualan.ui.cart.add

import com.example.penjualan.data.model.cart.ResponseCartUpdate

interface CartAddContract {

    interface Presenter {
        fun addCart(username:  String, kdProduk: Long, jumlah: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseCartUpdate: ResponseCartUpdate)
        fun showMessage(message: String)
    }

}