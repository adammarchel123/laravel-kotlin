package com.example.penjualan.ui.transaction

import com.example.penjualan.data.model.transaction.ResponseTransactionList
import com.example.penjualan.data.model.transaction.detail.ResponseTransactionDetail

interface TransactionContract {

    interface Presenter {
        fun getTransactionByUsername(username: String)
    }
    
    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseTransactionList: ResponseTransactionList)
        fun onClickTransaction(invoice: String)
    }

}