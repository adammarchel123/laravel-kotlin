package com.example.penjualan.ui.product

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.penjualan.R
import com.example.penjualan.data.model.product.DataCategory
import com.example.penjualan.data.model.product.DataProduct
import com.example.penjualan.data.model.product.ResponseCategoryList
import com.example.penjualan.data.model.product.ResponseProductList
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: ProductPresenter
    private var kdKategori: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        presenter = ProductPresenter(this)
        presenter.getCategory()
    }

    override fun initActivity() {
        supportActionBar!!.hide()

        productAdapter = ProductAdapter(this, arrayListOf())
        categoryAdapter =
            CategoryAdapter(this, arrayListOf()) { category: DataCategory, position: Int ->
                kdKategori = category.kd_kategori!!
                presenter.getProduct(category.kd_kategori!!)
            }
    }

    override fun initListener() {
        rcvCategory.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = categoryAdapter
        }

        rcvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
        }

        swipe.setOnRefreshListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> presenter.getCategory()
                rcvProduct.visibility ->presenter.getProduct(kdKategori)
            }
        }

        imvClose.setOnClickListener {
            when (View.VISIBLE) {
                rcvCategory.visibility -> finish()
                rcvProduct.visibility -> {
                    rcvProduct.visibility = View.GONE
                    rcvCategory.visibility = View.VISIBLE
                    txvCategory.text = "Pilih Kategori"
                }
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                swipe.isRefreshing = true
                rcvCategory.visibility = View.GONE
                rcvProduct.visibility = View.GONE
            }

            false -> swipe.isRefreshing = false
        }
    }


    override fun onResultCategory(responseCategoryList: ResponseCategoryList) {
        rcvCategory.visibility = View.VISIBLE
        val dataCategory: List<DataCategory> = responseCategoryList.dataCategory
        categoryAdapter.setData(dataCategory)
        txvCategory.text = "Pilih Kategori"
    }

    override fun onResultProduct(responseProductList: ResponseProductList) {
        rcvProduct.visibility = View.VISIBLE
        val dataProduct: List<DataProduct> = responseProductList.dataProduct
        productAdapter.setData(dataProduct)
        txvCategory.text = dataProduct[0].kategori
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when (View.VISIBLE) {
            rcvCategory.visibility -> finish()
            rcvProduct.visibility -> {
                rcvProduct.visibility = View.GONE
                rcvCategory.visibility = View.VISIBLE
                txvCategory.text = "Pilih Kategori"
            }
        }
    }
}































