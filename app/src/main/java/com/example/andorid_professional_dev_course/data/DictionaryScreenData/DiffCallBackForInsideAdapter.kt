package com.example.andorid_professional_dev_course.data.DictionaryScreenData

import androidx.recyclerview.widget.DiffUtil
import com.example.andorid_professional_dev_course.data.database.WordTableEntity

class DiffCallBackForInsideAdapter(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}