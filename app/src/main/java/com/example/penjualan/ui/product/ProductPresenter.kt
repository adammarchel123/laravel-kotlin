package com.example.penjualan.ui.product

import com.example.penjualan.data.model.product.ResponseCategoryList
import com.example.penjualan.data.model.product.ResponseProductList
import com.example.penjualan.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter(val view: ProductContract.View): ProductContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getCategory() {
        view.onLoading(true)
        ApiService.endPoint.getCategory()
            .enqueue(object : Callback<ResponseCategoryList> {

                override fun onFailure(call: Call<ResponseCategoryList>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseCategoryList>,
                    response: Response<ResponseCategoryList>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseCategoryList: ResponseCategoryList? = response.body()
                        view.onResultCategory(responseCategoryList!!)
                    }
                }

            })
    }

    override fun getProduct(kd_kategori: Long) {
        view.onLoading(true)
        ApiService.endPoint.getProductByCategory(kd_kategori)
            .enqueue(object : Callback<ResponseProductList> {

                override fun onFailure(call: Call<ResponseProductList>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseProductList>,
                    response: Response<ResponseProductList>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {
                        val responseProductList: ResponseProductList? = response.body()
                        view.onResultProduct(responseProductList!!)
                    }
                }

            })
    }


}
