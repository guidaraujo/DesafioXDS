package com.example.desafioxds.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioxds.R
import com.example.desafioxds.contract.SplashContract
import com.example.desafioxds.databinding.ActivitySplashBinding
import com.example.desafioxds.presenter.SplashPresenter

class SplashActivity : AppCompatActivity(), SplashContract.View {
    private lateinit var bind:ActivitySplashBinding

    override lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)
        presenter = SplashPresenter(this, this)
        presenter.start()

    }

    override fun toLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun toPizzaList() {
        startActivity(Intent(this, PizzaListActivity::class.java))
        finish()
    }

    override fun setupView() {

    }
}