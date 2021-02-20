package com.example.penjualan.ui.transaction.detail

import com.example.penjualan.data.model.transaction.ResponseTransactionList
import com.example.penjualan.data.model.transaction.detail.ResponseTransactionDetail

interface TransactionDetailContract {

    interface Presenter {
        fun getTransactionByInvoice(invoice: String)
    }
    
    interface View {
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseTransactionDetail: ResponseTransactionDetail)
    }

}