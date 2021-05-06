package com.example.desafioxds.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioxds.R
import com.example.desafioxds.adapter.PizzaListAdapter
import com.example.desafioxds.contract.PizzaListContract
import com.example.desafioxds.databinding.ActivityPizzaListBinding
import com.example.desafioxds.model.PizzaResponse
import com.example.desafioxds.presenter.PizzaListPresenter
import com.example.desafioxds.utils.Utils

class PizzaListActivity : AppCompatActivity(), PizzaListContract.View, PizzaListAdapter.SelectItem {
    private lateinit var bind:ActivityPizzaListBinding
    override lateinit var presenter: PizzaListPresenter
    private lateinit var adapter: PizzaListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityPizzaListBinding.inflate(layoutInflater)
        setContentView(bind.root)

        presenter = PizzaListPresenter(this, this)
        presenter.start()
        Utils.showLoadingDialog(this)
        presenter.loadData()
    }

    override fun onFailureRequest(message: String) {
        Utils.toast(this, message)
    }

    override fun onSuccessRequest(list:ArrayList<PizzaResponse>) {
        adapter.addAll(list)
    }

    override fun setupView() {
        setSupportActionBar(bind.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        adapter = PizzaListAdapter(this)
        with(bind){
            recycler.layoutManager = LinearLayoutManager(this@PizzaListActivity)
            recycler.adapter = adapter
        }

        bind.editSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter(p0.toString())
            }
            override fun afterTextChanged(text: Editable?) {}
        })
    }

    override fun onSelectedItem(selectedItem:PizzaResponse) {
        presenter.toCheckout(selectedItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                startActivity(
                    Intent(
                        this@PizzaListActivity,
                        LoginActivity::class.java
                    )
                )
                presenter.logout()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}