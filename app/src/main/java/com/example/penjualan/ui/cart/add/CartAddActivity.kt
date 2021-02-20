package com.example.penjualan.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan.R
import com.example.penjualan.data.Constant
import com.example.penjualan.data.database.PrefsManager
import com.example.penjualan.data.model.cart.ResponseCartUpdate
import com.example.penjualan.ui.product.ProductActivity
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    lateinit var cartAddPresenter: CartAddPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)
        prefsManager = PrefsManager(this)
        cartAddPresenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if (Constant.IS_CHANGED) {
            Constant.IS_CHANGED = false
            edtProduct.setText(Constant.PRODUCT_NAME)
            txvQty.visibility = View.VISIBLE
            npQuantity.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_ID = 0
        Constant.PRODUCT_NAME = ""
        Constant.PRODUCT_QTY = 0
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        txvQty.visibility = View.GONE
        npQuantity.visibility = View.GONE
    }

    override fun initListener() {
        edtProduct.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        npQuantity.minValue = 1
        npQuantity.maxValue = 25
        npQuantity.wrapSelectorWheel = true
        npQuantity.setOnValueChangedListener {picker, oldVal, newVal ->
            Constant.PRODUCT_QTY = newVal.toLong()
        }

        btnSubmit.setOnClickListener {
            if (Constant.PRODUCT_ID > 0) {
                Constant.PRODUCT_QTY = if (Constant.PRODUCT_QTY > 0) Constant.PRODUCT_QTY else 1
                cartAddPresenter.addCart(
                    prefsManager.prefsUsername, Constant.PRODUCT_ID, Constant.PRODUCT_QTY
                )
            } else {
                edtProduct.error = "Tidak boleh kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSubmit.visibility = View.GONE
            }

            false -> {
                progress.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseCartUpdate: ResponseCartUpdate) {
        if (responseCartUpdate.status) {
            Constant.IS_CHANGED = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}