package com.example.desafioxds.presenter

import android.content.Context
import android.util.Log
import com.example.desafioxds.api.ApiRequest
import com.example.desafioxds.api.ResponseListener
import com.example.desafioxds.contract.LoginContract
import com.example.desafioxds.model.LoginResponse
import com.example.desafioxds.utils.SharedPrefUtil
import org.json.JSONObject
import java.lang.Exception

class LoginPresenter(val view: LoginContract.View, private var context:Context) : LoginContract.Presenter {

    override fun start() {
        view.setupView()
    }

    override fun login(user:String, pass:String) {

        ApiRequest(context).login(user, pass, object : ResponseListener {
            override fun onSuccess(o: Any) {
                if (o is LoginResponse) {
                    SharedPrefUtil.saveString(
                        SharedPrefUtil.KEY_TOKEN,
                        o.accessToken,
                        context
                    )
                    view.onSuccessLogin()
                }


            }

            override fun onFailure(errorMessage: String) {
                view.onFailureLogin(errorMessage)
            }
        })
    }

}