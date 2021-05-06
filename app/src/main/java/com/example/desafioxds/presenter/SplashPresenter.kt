package com.example.desafioxds.presenter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.desafioxds.contract.SplashContract
import com.example.desafioxds.utils.SharedPrefUtil

class SplashPresenter(val view:SplashContract.View, val context: Context):SplashContract.Presenter {
    companion object{
        const val SPLASH_TIME_OUT = 1500
    }

    override fun verifyLogin() {
        var token = SharedPrefUtil.getString(SharedPrefUtil.KEY_TOKEN, context)

        Handler(Looper.getMainLooper()).postDelayed({
            if (!token.isNullOrEmpty()){
                view.toPizzaList()
            }else{
                view.toLogin()
            }
        }, SPLASH_TIME_OUT.toLong())

    }

    override fun start() {
        verifyLogin()
    }
}