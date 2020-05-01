package com.accenture.marvel.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.accenture.marvel.R
import com.accenture.marvel.character.CharacterActivity
import com.accenture.marvel.model.Character
import com.accenture.marvel.util.AspectRatio
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.item_character.view.*

class MainAdapter : PagedListAdapter<Character, MainAdapter.ViewHolder>(diffCallback)  {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val context = view.context
        private lateinit var item: Character

        init {
            view.setOnClickListener(this)
        }

        private val name = view.tv_name
        private val avatar = view.iv_avatar

        fun bind(item: Character) {
            this.item = item

            name.text = item.name
            val url = "${item.thumbnail.path}/${AspectRatio.MEDIUM.value}.${item.thumbnail.extension}"
            avatar.load(url)
        }

        override fun onClick(v: View?) {
            val bundleExtras = Bundle()
            bundleExtras.putParcelable(Extra.CHARACTER.value, this.item)

            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtras(bundleExtras)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}