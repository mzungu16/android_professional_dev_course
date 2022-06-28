package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.data.DictionaryScreenData.DiffCallBackDScreen
import com.example.andorid_professional_dev_course.data.database.Converter
import com.example.andorid_professional_dev_course.data.database.WordTableEntity

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {
    var list: List<WordTableEntity> = listOf()
        set(listParam) {
            val diffCallBack = DiffCallBackDScreen(this.list, listParam)
            DiffUtil.calculateDiff(diffCallBack).also {
                it.dispatchUpdatesTo(this)
            }
            field = listParam
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dictionary_item, parent, false)
        return DictionaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DictionaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.item_image)
        private val inputText = itemView.findViewById<TextView>(R.id.text_res)
        private val translatedText = itemView.findViewById<TextView>(R.id.result_of_translate)
        private val posText = itemView.findViewById<TextView>(R.id.result_of_pos)
//        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler_view_inside)
        val insideAdapter = InsideAdapter()

        fun bind(item: WordTableEntity) {
            Glide.with(itemView)
                .load("https://picsum.photos/200/300")
                .into(image)
            inputText.text = item.word
            translatedText.text = item.translation
            posText.text = item.pos
//            insideAdapter.list = Converter().fromJsonToListString(item.synonyms)
//
//            recyclerView.apply {
//                layoutManager = LinearLayoutManager(context)
//                adapter = insideAdapter
//            }
//            recyclerView.visibility = View.VISIBLE
        }
    }
}