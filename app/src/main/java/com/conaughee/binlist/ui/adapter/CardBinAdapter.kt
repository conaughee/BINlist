package com.conaguhee.binlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.conaguhee.binlist.dto.CardBin
import com.conaughee.binlist.databinding.BinHistoryItemBinding

interface OnInteractionListener {
    fun onRemove(bin: CardBin)
}

class CardBinAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<CardBin, CardBinViewHolder>(CardBinDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBinViewHolder {
        val binding = BinHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardBinViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: CardBinViewHolder, position: Int) {
        val bin = getItem(position)
        holder.bind(bin)
    }
}

class CardBinViewHolder(
    private val binding: BinHistoryItemBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(bin: CardBin) {
        with(binding) {
            binNumber.text = bin.bin.toString()

            btnRemove.setOnClickListener {
                onInteractionListener.onRemove(bin)
            }
        }
    }
}

class CardBinDiffCallback : DiffUtil.ItemCallback<CardBin>() {
    override fun areItemsTheSame(oldItem: CardBin, newItem: CardBin) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CardBin, newItem: CardBin) = oldItem == newItem
}