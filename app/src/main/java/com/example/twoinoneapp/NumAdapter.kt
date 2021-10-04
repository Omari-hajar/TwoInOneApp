package com.example.twoinoneapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twoinoneapp.databinding.NumberMainBinding

class NumAdapter(private val list: ArrayList<String>): RecyclerView.Adapter<NumAdapter.ViewHolder>() {
    class ViewHolder ( val binding: NumberMainBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = NumberMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvGame.text = item
        }

    }

    override fun getItemCount()= list.size

}