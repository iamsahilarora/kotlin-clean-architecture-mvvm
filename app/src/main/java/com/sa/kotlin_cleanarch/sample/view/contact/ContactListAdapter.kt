package com.sa.kotlin_cleanarch.sample.view.contact

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sa.kotlin_cleanarch.sample.model.bean.Contact
import com.sa.kotlin_cleanarch.sample.databinding.RowContactBinding

/* Created by Sahil Bharti on 10/5/19.
 *
*/
class ContactListAdapter(private val context: Context, private val arrList: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(RowContactBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(arrList[position])
    }


    /** View Holders */
    inner class MyViewHolder(private val binding: RowContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact) {
            binding.contact = item
            Glide.with(context).load(item.avatar).into(binding.ivAvtar)
        }
    }


}