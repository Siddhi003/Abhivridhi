package com.example.draw_menu

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


abstract class GenericAdapter<T>(private val context : Context, private val arrayList : List<T>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract val layoutResourceId : Int
    abstract fun onDataBind(model : T, position : Int, holder : RecyclerView.ViewHolder?)
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
        return ItemViewHolder(view)
    }

    var onItemClickListener : OnItemClickListener? = null


    interface OnItemClickListener {

        fun <T>onItemClick(tagName : T, view : View?, position : Int)
    }

    fun setOnItemClickListenera(onItemClickListener : OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener

    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
        onDataBind(arrayList[position], position, holder)
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener { onItemClickListener!!.onItemClick<T>(arrayList[position], holder.itemView,
                position) }
        }
    }

    override fun getItemCount() : Int {
        return arrayList.size
    }

    internal inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}