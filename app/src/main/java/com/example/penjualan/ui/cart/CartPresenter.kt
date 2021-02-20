package com.example.penjualan.ui.cart

import com.example.penjualan.data.model.cart.ResponseCartList
import com.example.penjualan.data.model.cart.ResponseCartUpdate
import com.example.penjualan.data.model.cart.ResponseCheckout
import com.example.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter(val view: CartContract.View) : CartContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoadingCart(true)
        ApiService.endPoint.getCart(username)
            .enqueue(object : Callback<ResponseCartList> {

                override fun onFailure(call: Call<ResponseCartList>, t: Throwable) {
                    view.onLoadingCart(false)
                }

                override fun onResponse(
                    call: Call<ResponseCartList>,
                    response: Response<ResponseCartList>
                ) {
                    view.onLoadingCart(false)
                    if (response.isSuccessful) {
                        val responseCartList: ResponseCartList? = response.body()
                        view.onResultCart(responseCartList!!)
                    }
                }

            })
    }

    override fun deleteItemCart(kd_keranjang: Long) {
        ApiService.endPoint.deleteItemCart(kd_keranjang)
            .enqueue(object : Callback<ResponseCartUpdate> {

                override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ResponseCartUpdate>,
                    response: Response<ResponseCartUpdate>
                ) {
                    if (response.isSuccessful) {
                        val responseCartUpdate: ResponseCartUpdate? = response.body()
                        view.onResultDelete(responseCartUpdate!!)
                    }
                }

            })
    }

    override fun deleteCart(username: String) {
        ApiService.endPoint.deleteCart(username)
            .enqueue(object : Callback<ResponseCartUpdate> {

                override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ResponseCartUpdate>,
                    response: Response<ResponseCartUpdate>
                ) {
                    if (response.isSuccessful) {
                        val responseCartUpdate: ResponseCartUpdate? = response.body()
                        view.onResultDelete(responseCartUpdate!!)
                        view.showMessage(responseCartUpdate.msg)
                    }
                }

            })
    }

    override fun checkout(username: String, kd_agent: Long) {
        view.onLoadingCart(true)
        ApiService.endPoint.checkout(username, kd_agent)
            .enqueue(object : Callback<ResponseCheckout> {

                override fun onFailure(call: Call<ResponseCheckout>, t: Throwable) {
                    view.onLoadingCart(false)
                }

                override fun onResponse(
                    call: Call<ResponseCheckout>,
                    response: Response<ResponseCheckout>
                ) {
                    view.onLoadingCart(false)
                    if (response.isSuccessful) {
                        val responseCheckout: ResponseCheckout? = response.body()
                        view.onResultCheckout(responseCheckout!!)
                        view.showMessage(responseCheckout.msg)
                    }
                }

            })
    }
}






































