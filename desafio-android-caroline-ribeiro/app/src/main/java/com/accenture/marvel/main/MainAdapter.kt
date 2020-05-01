package com.accenture.marvel.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.marvel.R
import com.accenture.marvel.detail.DetailActivity
import com.accenture.marvel.network.model.Result
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.item_character.view.*

class MainAdapter(private var items: List<Result>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val context = view.context
        private lateinit var item: Result

        init {
            view.setOnClickListener(this)
        }

        private val name = view.tv_name
        private val avatar = view.iv_avatar

        fun bind(item: Result) {
            this.item = item

            name.text = item.name
            val url = "${item.thumbnail.path}/portrait_medium.${item.thumbnail.extension}"
            avatar.load(url)
        }

        override fun onClick(v: View?) {
            val bundleExtras = Bundle()
            bundleExtras.putParcelable("details", this.item)

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtras(bundleExtras)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun updateItems(newItems: List<Result>) {
        this.items = newItems
        this.notifyDataSetChanged()
    }
}