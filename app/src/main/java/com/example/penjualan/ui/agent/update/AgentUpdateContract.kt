package com.example.penjualan.ui.agent.update

import com.example.penjualan.data.model.agent.ResponseAgentDetail
import com.example.penjualan.data.model.agent.ResponseAgentUpdate
import java.io.File

interface AgentUpdateContract {

    interface Presenter {
        fun getDetail(kd_agen: Long)
        fun updateAgent(
            kd_agen: Long,
            nama_toko: String,
            nama_pemilik: String,
            alamat: String,
            latitude: String,
            longitude: String,
            gambar_toko: File?
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responseAgentDetail: ResponseAgentDetail)
        fun onResultUpdate(responseAgentUpdate: ResponseAgentUpdate)
        fun showMessage(message: String)
    }

}