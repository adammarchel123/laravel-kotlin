package com.example.penjualan.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan.R
import com.example.penjualan.data.model.cart.DataCart
import com.example.penjualan.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_cart.view.*
import java.text.NumberFormat
import java.util.*

class CartAdapter(
    val context: Context,
    var cart: ArrayList<DataCart>,
    val clickListener: (DataCart, Int) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
    )

    override fun getItemCount() = cart.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(cart[position])
        GlideHelper.setImage(context, cart[position].gambar_produk!!, holder.view.imvImage)
        holder.view.imvDelete.setOnClickListener {
            clickListener(cart[position], position)
            removeCart(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(cart: DataCart) {
            view.txvCategory.text = cart.kategori
            view.txvNameProduct.text = cart.nama_produk
            view.txvPrice.text = "${cart.harga_rupiah} x${cart.jumlah}"

            val total: Double = cart.jumlah!!.toDouble() * cart.harga!!.toDouble()
            val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
            view.txvTotal.text = "Rp $totalRupiah"
        }
    }

    fun setData(newCart: List<DataCart>) {
        cart.clear()
        cart.addAll(newCart)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int) {
        cart.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, cart.size)
    }

    fun removeAll() {
        cart.clear()
        notifyDataSetChanged()
    }
}






























