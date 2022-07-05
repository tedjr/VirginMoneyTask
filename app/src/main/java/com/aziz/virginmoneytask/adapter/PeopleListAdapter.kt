package com.aziz.virginmoneytask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aziz.virginmoneytask.databinding.LayoutPeopleItemBinding
import com.aziz.virginmoneytask.model.PeopleItem
import com.aziz.virginmoneytask.utils.loadImagefromUrl

class PeopleListAdapter(
    private val listofPeople:MutableList<PeopleItem> = mutableListOf()
): RecyclerView.Adapter<PeopleViewHolder>() {

    var onItemClick: ((PeopleItem) -> Unit)? = null

    fun loadData(listOfAlerts: MutableList<PeopleItem>){
        this.listofPeople.clear()
        this.listofPeople.addAll(listOfAlerts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(LayoutPeopleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.setAlertData(listofPeople[position])
        holder.itemView.rootView.setOnClickListener {
            onItemClick?.invoke(listofPeople[position])
        }
    }

    override fun getItemCount(): Int {
        return listofPeople.size
    }
}

class PeopleViewHolder(itemView: LayoutPeopleItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    val binding=itemView
    fun setAlertData(peopleItem: PeopleItem) {
        binding.personName.text=" ${peopleItem.firstName} ${peopleItem.lastName}"
        binding.personImage.loadImagefromUrl(peopleItem.avatar)
        binding.personEmail.text=peopleItem.email
        binding.personTelephone.text=peopleItem.phone
    }
}