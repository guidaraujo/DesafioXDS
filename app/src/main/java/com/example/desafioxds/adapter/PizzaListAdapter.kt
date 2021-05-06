package com.example.desafioxds.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioxds.R
import com.example.desafioxds.databinding.LayoutItemRecyclerMainBinding
import com.example.desafioxds.model.PizzaResponse
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.logging.Filter

class PizzaListAdapter(val listener: SelectItem) :
    RecyclerView.Adapter<PizzaListAdapter.PizzaListViewHolder>() {
    private var listPizza: ArrayList<PizzaResponse> = ArrayList()

    private var initialList: ArrayList<PizzaResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_recycler_main, parent, false)

        return PizzaListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaListViewHolder, position: Int) {
        var item = listPizza[position]

        holder.bind.ratingPizza.rating = item.rating!!
        holder.bind.textPizzaName.text = item.name!!

        try {
            holder.bind.textPizzaPrize.text = formatValue(item.priceP!!)
        } catch (e: Exception) {

        }

        Picasso.get().load(item.imageUrl).into(holder.bind.imgPizza)

        holder.bind.cardView.setOnClickListener {
            listener.onSelectedItem(item)
        }

    }

    override fun getItemCount(): Int {
        return listPizza.size
    }

    fun filter(filter: String) {
        if (filter.trim() == "") {
            listPizza.clear()
            listPizza.addAll(initialList)
            Log.i("teste", "addAll1: "+initialList.size)
            notifyDataSetChanged()
            return
        }
        Log.i("teste", "addAll: "+initialList.size)
        listPizza.clear()

        for (item in initialList) {
            if (item.name != null) {
                if (item.name!!.trim().toLowerCase().contains(filter.trim().toLowerCase())){
                    Log.i("teste", "filter: "+filter.trim().toLowerCase())
                    Log.i("teste", "filter: "+item.name!!.trim().toLowerCase())
                    listPizza.add(item)
                    Log.i("teste", "filter--------------: "+listPizza.size)
                }
            }
        }

        notifyDataSetChanged()

    }

    fun addAll(list: ArrayList<PizzaResponse>) {
        if (initialList.size == 0) {
            initialList = list
            Log.i("teste", "addAll: "+initialList.size)
        }
        this.listPizza.clear()
        this.listPizza.addAll(list)
        notifyDataSetChanged()
    }

    private fun formatValue(value: Float): String {
        return NumberFormat.getCurrencyInstance().format(value.toDouble())
    }

    interface SelectItem {
        fun onSelectedItem(selectedItem: PizzaResponse)
    }

    inner class PizzaListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var bind = LayoutItemRecyclerMainBinding.bind(itemView)
    }
}