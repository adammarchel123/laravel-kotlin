package com.example.penjualan.ui.login

import com.example.penjualan.data.database.PrefsManager
import com.example.penjualan.data.model.login.DataLogin
import com.example.penjualan.data.model.login.ResponseLogin

interface LoginContract {

    interface Presenter {
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }
}