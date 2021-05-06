package com.example.desafioxds.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.desafioxds.activity.CheckoutActivity
import com.example.desafioxds.adapter.PizzaListAdapter
import com.example.desafioxds.api.ApiRequest
import com.example.desafioxds.api.ResponseListener
import com.example.desafioxds.contract.PizzaListContract
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.utils.SharedPrefUtil
import com.example.desafioxds.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class PizzaListPresenter(val context: Context, val view:PizzaListContract.View) :PizzaListContract.Presenter{
    companion object{
        const val MODEL = "MODEL"
    }
    override fun loadData() {

        ApiRequest(context).getPizzaList(object : ResponseListener {
            override fun onSuccess(o: Any) {
                if (o is ArrayList<*>) {
                    Utils.hideLoadingDialog()
                    view.onSuccessRequest(o as ArrayList<PizzaResponse>)
                }
            }

            override fun onFailure(errorMessage: String) {
                Utils.hideLoadingDialog()
                view.onFailureRequest(errorMessage)
            }
        })
    }

    override fun toCheckout(itemPizzaResponse: PizzaResponse) {
        val intent = Intent(context, CheckoutActivity::class.java)

        intent.putExtra(MODEL, itemPizzaResponse)
        context.startActivity(intent)
    }

    override fun start() {
        view.setupView()
    }

    override fun logout() {
        SharedPrefUtil.saveString(SharedPrefUtil.KEY_TOKEN, "", context)
    }
}