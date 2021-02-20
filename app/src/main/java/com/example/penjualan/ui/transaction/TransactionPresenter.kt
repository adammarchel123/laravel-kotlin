package com.example.penjualan.ui.transaction

import com.example.penjualan.data.model.transaction.ResponseTransactionList
import com.example.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionPresenter(val view: TransactionContract.View) : TransactionContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getTransactionByUsername(username: String) {
        view.onLoading(true)
        ApiService.endPoint.getTransactionByUserName(username)
            .enqueue(object : Callback<ResponseTransactionList> {

                override fun onFailure(call: Call<ResponseTransactionList>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseTransactionList>,
                    response: Response<ResponseTransactionList>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseTransactionList: ResponseTransactionList? = response.body()
                        view.onResult(responseTransactionList!!)
                    }
                }

            })
    }


}