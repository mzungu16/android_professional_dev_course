package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andorid_professional_dev_course.R

class InsideAdapter : RecyclerView.Adapter<InsideAdapter.InsideViewHolder>() {
    var list: List<String> = listOf()
    inner class InsideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textValue = itemView.findViewById<TextView>(R.id.item_text)
        fun bind(item: String) {
            textValue.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsideViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return InsideViewHolder(view)
    }

    override fun onBindViewHolder(holder: InsideViewHolder, position: Int) {
        holder.bind(list[position])
        println("TAG $list")
    }

    override fun getItemCount() = list.size
}