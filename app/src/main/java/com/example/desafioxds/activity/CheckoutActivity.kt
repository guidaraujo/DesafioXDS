package com.example.desafioxds.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioxds.R
import com.example.desafioxds.contract.CheckoutContract
import com.example.desafioxds.databinding.ActivityCheckoutBinding
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.presenter.CheckoutPresenter
import com.example.desafioxds.utils.Utils
import com.squareup.picasso.Picasso

class CheckoutActivity : AppCompatActivity(), CheckoutContract.View {
    override lateinit var presenter: CheckoutPresenter
    private lateinit var bind:ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(bind.root)

        presenter = CheckoutPresenter(bind, this, this, this)
        presenter.start()
        presenter.loadData(intent)
    }

    override fun setData(item: PizzaResponse?) {
        Picasso.get().load(item?.imageUrl).into(bind.imgPizza)
        bind.textName.text = item?.name
        bind.ratingPizza.rating = item?.rating!!
    }

    override fun showErrorMessage(message: String, finish:Boolean) {
        Utils.toast(this, message)
        if (finish){
            finish()
        }
    }

    override fun setupView() {
        with(bind){
            btnP.setOnClickListener {
                presenter.changeButtonColor(findViewById(it.id), "P")
            }
            btnM.setOnClickListener {
                presenter.changeButtonColor(findViewById(it.id), "M")
            }
            btnG.setOnClickListener {
                presenter.changeButtonColor(findViewById(it.id), "G")
            }

            btnComprar.setOnClickListener {
                presenter.showDialog()
            }
        }
    }
}