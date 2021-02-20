package com.example.penjualan.network

import com.example.penjualan.data.model.login.ResponseLogin
import com.example.penjualan.data.model.agent.ResponseAgentDetail
import com.example.penjualan.data.model.agent.ResponseAgentList
import com.example.penjualan.data.model.agent.ResponseAgentUpdate
import com.example.penjualan.data.model.cart.ResponseCartList
import com.example.penjualan.data.model.cart.ResponseCartUpdate
import com.example.penjualan.data.model.cart.ResponseCheckout
import com.example.penjualan.data.model.product.ResponseCategoryList
import com.example.penjualan.data.model.product.ResponseProductList
import com.example.penjualan.data.model.transaction.ResponseTransactionList
import com.example.penjualan.data.model.transaction.detail.ResponseTransactionDetail
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("agen")
    fun getAgent(): Call<ResponseAgentList>

    @Multipart
    @POST("agen")
    fun insertAgent(
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambar_toko: MultipartBody.Part
    ): Call<ResponseAgentUpdate>

    @GET("agen/{kd_agen}")
    fun getAgentDetail(
        @Path("kd_agen") kd_agen: Long
    ): Call<ResponseAgentDetail>

    @Multipart
    @POST("agen/{kd_agen}")
    fun updateAgent(
        @Path("kd_agen") kd_agen: Long,
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part gambar_toko: MultipartBody.Part?,
        @Query("_method") _method: String
    ): Call<ResponseAgentUpdate>

    @DELETE("agen/{kd_agen}")
    fun deleteAgent(
        @Path("kd_agen") kd_agen: Long
    ): Call<ResponseAgentUpdate>

    @FormUrlEncoded
    @POST("get_transaksi")
    fun getTransactionByUserName(
        @Field("username") username: String
    ): Call<ResponseTransactionList>

    @FormUrlEncoded
    @POST("get_detail_transaksi")
    fun getTransactionByInvoice(
        @Field("no_faktur") no_faktur: String
    ): Call<ResponseTransactionDetail>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(
        @Field("username") username: String
    ): Call<ResponseCartList>

    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(
        @Field("username") username: String,
        @Field("kd_produk") kd_produk: Long,
        @Field("jumlah") jumlah: Long
    ): Call<ResponseCartUpdate>

    @GET("get_kategori")
    fun getCategory(): Call<ResponseCategoryList>

    @FormUrlEncoded
    @POST("get_produk")
    fun getProductByCategory(
        @Field("kd_kategori") kd_kategori: Long
    ): Call<ResponseProductList>

    @FormUrlEncoded
    @POST("delete_item_cart")
    fun deleteItemCart(
        @Field("kd_keranjang") kd_keranjang: Long
    ): Call<ResponseCartUpdate>

    @FormUrlEncoded
    @POST("delete_cart")
    fun deleteCart(
        @Field("username") username: String
    ): Call<ResponseCartUpdate>

    @GET("search_agen")
    fun searchAgent(
        @Query("keyword") keyword: String
    ): Call<ResponseAgentList>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("username") username: String,
        @Field("kd_agen") kd_agen: Long
    ): Call<ResponseCheckout>
}









































