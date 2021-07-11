package com.temsipatrin.sampleforinterview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.temsipatrin.sampleforinterview.databinding.ItemCharacterBinding
import com.temsipatrin.sampleforinterview.ui.models.CharacterShortUi

class CharactersAdapter(private val onClickListener: OnCardClickListener) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    var data = listOf<CharacterShortUi>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onClickListener)
    }

    class ViewHolder private constructor(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterShortUi, onClickListener: OnCardClickListener) {
            binding.cardCharacter.setOnClickListener { onClickListener.onItemClick(item.id) }
            binding.name.text = item.name
            binding.status.text = item.status
            binding.status.setTextColor(item.statusColor)
            binding.species.text = item.species
            binding.gender.text = item.gender
            Glide.with(this.itemView.context)
                .load(item.image)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                .into(binding.image)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface OnCardClickListener {
        fun onItemClick(id: Int)
    }
}