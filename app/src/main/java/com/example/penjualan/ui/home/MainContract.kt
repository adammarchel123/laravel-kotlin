package com.example.penjualan.ui.home

interface MainContract {

    interface View {
        fun initListener()
        fun showMessage(message: String)
    }
}