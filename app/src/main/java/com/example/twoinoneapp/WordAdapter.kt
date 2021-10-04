package com.example.twoinoneapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twoinoneapp.databinding.WordItemBinding

class WordAdapter(private val list: ArrayList<String>): RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    class ViewHolder(val binding: WordItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.binding.tvItem.text = item

    }

    override fun getItemCount()= list.size
}