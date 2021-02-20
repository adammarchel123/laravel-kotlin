package com.example.penjualan.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.penjualan.R
import com.example.penjualan.data.database.PrefsManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: UserPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        prefsManager = PrefsManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefsManager)
    }

    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btnBack.setOnClickListener {
            finish()
        }
        txvLogout.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {
        txvUsername.text = prefsManager.prefsUsername
        txvName.text = prefsManager.prefsNamaPegawai
        txvAddress.text = prefsManager.prefsAlamat
        txvGender.text = prefsManager.prefsJk
    }

    override fun onResultLogout() {
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }


}











