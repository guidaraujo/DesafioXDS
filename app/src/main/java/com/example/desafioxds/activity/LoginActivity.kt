package com.example.desafioxds.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioxds.R
import com.example.desafioxds.contract.LoginContract
import com.example.desafioxds.databinding.ActivityLoginBinding
import com.example.desafioxds.presenter.LoginPresenter
import com.example.desafioxds.utils.SharedPrefUtil
import com.example.desafioxds.utils.Utils

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override lateinit var presenter: LoginPresenter
    private lateinit var bind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        presenter = LoginPresenter(this, this)
        presenter.start()
    }

    override fun onFailureLogin(message: String) {
        Utils.hideLoadingDialog()
        Utils.toast(this, message)
    }

    override fun onSuccessLogin() {
        Utils.hideLoadingDialog()
        startActivity(Intent(this, PizzaListActivity::class.java))
    }

    override fun setupView() {
        with(bind){
            btnLogin.setOnClickListener {
                Utils.showLoadingDialog(this@LoginActivity)
                presenter.login(bind.editUser.text.toString(), bind.editPassword.text.toString())
            }

            editPassword.setOnEditorActionListener { textView, i, keyEvent ->
                if (keyEvent == null || keyEvent.keyCode != android.view.KeyEvent.KEYCODE_ENTER) {
                    false
                }
                Utils.showLoadingDialog(this@LoginActivity)
                presenter.login(bind.editUser.text.toString(), bind.editPassword.text.toString())
                true
            }
        }
        }

}