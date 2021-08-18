package com.miguelsantos.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguelsantos.businesscard.data.BusinessCard
import com.miguelsantos.businesscard.databinding.ItemCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.BusinessCardViewHolder>(DiffCallBack()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BusinessCardViewHolder(
        ItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BusinessCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BusinessCardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            with(binding) {
                cardTxtName.text = item.name
                cardTxtTelephone.text = item.telephone
                cardTxtEmail.text = item.email
                cardTxtCompanyName.text = item.company
                cardView.apply {
                    setBackgroundColor(Color.parseColor(item.backgroundColor))
                    setOnClickListener { listenerShare(it) }
                }
            }
        }

    }
}

class DiffCallBack : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean =
        oldItem.id == newItem.id
}
