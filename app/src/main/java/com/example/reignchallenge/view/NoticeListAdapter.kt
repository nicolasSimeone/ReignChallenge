package com.example.reignchallenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.R
import com.example.reignchallenge.model.NoticesSaved
import com.example.reignchallenge.utils.TimeAgo
import com.example.reignchallenge.utils.formatName
import kotlinx.android.synthetic.main.list_item.view.*

class NoticeListAdapter(private val context: Context, private var noticeList: MutableList<NoticesSaved>): RecyclerView.Adapter<NoticeListAdapter.ViewHolder>() {

    var onItemClick:((NoticesSaved) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    fun refreshList(noticeList: MutableList<NoticesSaved>) {
        this.noticeList.clear()
        this.noticeList = noticeList.toMutableList()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) : NoticesSaved {
        return noticeList[position]
    }


    fun removeAt(position: Int) {
        noticeList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: NoticeListAdapter.ViewHolder, position: Int) {

        if(noticeList[position].title.isNullOrEmpty())
        {
            holder.txtTitle.text = noticeList[position].story_title
        }
        else {
            holder.txtTitle.text = noticeList[position].title
        }

        holder.txtSubtitle.text = formatName(noticeList[position])
    }

    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        var txtTitle: TextView
        var txtSubtitle: TextView

        init {
            txtTitle = itemView.title
            txtSubtitle = itemView.subtitle
            itemView.setOnClickListener {
                onItemClick?.invoke(noticeList[adapterPosition])
            }
        }

    }

}