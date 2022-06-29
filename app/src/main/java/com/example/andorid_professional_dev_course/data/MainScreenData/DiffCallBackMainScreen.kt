package com.example.andorid_professional_dev_course.data.MainScreenData

import androidx.recyclerview.widget.DiffUtil

class DiffCallBackMainScreen(
    private val oldList: List<Synonyms>,
    private val newList: List<Synonyms>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}