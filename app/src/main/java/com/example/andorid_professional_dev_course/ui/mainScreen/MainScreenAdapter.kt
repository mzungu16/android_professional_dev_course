package com.example.andorid_professional_dev_course.ui.mainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.data.MainScreenData.DiffCallBackMainScreen
import com.example.andorid_professional_dev_course.data.MainScreenData.Synonyms

class MainScreenAdapter : RecyclerView.Adapter<MainScreenAdapter.MainScreenViewHolder>() {
    var list: List<Synonyms> = listOf()
        set(listParam) {
            val diffCallBack = DiffCallBackMainScreen(this.list, listParam)
            DiffUtil.calculateDiff(diffCallBack).also {
                it.dispatchUpdatesTo(this)
            }
            field = listParam
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MainScreenViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount() = list.size

    inner class MainScreenViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textValue = view.findViewById<TextView>(R.id.item_text)
        fun binding(item: Synonyms) {
            textValue.text = item.text
        }
    }
}