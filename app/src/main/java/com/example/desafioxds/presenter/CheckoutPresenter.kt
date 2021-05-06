package com.example.desafioxds.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.desafioxds.R
import com.example.desafioxds.contract.CheckoutContract
import com.example.desafioxds.databinding.ActivityCheckoutBinding
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.utils.SimpleDialog
import com.example.desafioxds.utils.Utils

class CheckoutPresenter(private val bind: ActivityCheckoutBinding, val context: Context,
                        val activity:FragmentActivity,val view:CheckoutContract.View)
    :CheckoutContract.Presenter, SimpleDialog.BackButtonListener {

    private var sizePizza=""

    private var item:PizzaResponse?=null

    override fun loadData(intent: Intent) {
        item = intent.extras?.getParcelable(PizzaListPresenter.MODEL)
        if (item!=null)
            view.setData(item)
        else
            view.showErrorMessage("Erro ao recuperar dados", true)
    }

    override fun changeButtonColor(view: Button, size:String) {
        bind.btnG.background = context.getDrawable(R.drawable.button_bg_pizza_size)
        bind.btnG.setTextColor(context.getColor(R.color.black))
        bind.btnM.background = context.getDrawable(R.drawable.button_bg_pizza_size)
        bind.btnM.setTextColor(context.getColor(R.color.black))
        bind.btnP.background = context.getDrawable(R.drawable.button_bg_pizza_size)
        bind.btnP.setTextColor(context.getColor(R.color.black))

        view.background = context.getDrawable(R.drawable.button_bg_pizza_size_green)
        view.setTextColor(context.getColor(R.color.white))

        sizePizza = size
    }

    override fun showDialog() {
        if (sizePizza.isEmpty()){
            view.showErrorMessage("Selecione um tamanho de pizza")
            return
        }

        Utils.showLoadingDialog(activity)
        Handler(Looper.getMainLooper()).postDelayed({
            SimpleDialog.show(activity, this)
            Utils.hideLoadingDialog()
        }, SplashPresenter.SPLASH_TIME_OUT.toLong())
    }

    override fun start() {
        view.setupView()
    }

    override fun onBackPressed() {
        activity.finish()
    }
}