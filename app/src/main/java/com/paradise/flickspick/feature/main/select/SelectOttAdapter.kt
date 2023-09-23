package com.paradise.flickspick.feature.main.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paradise.flickspick.R
import com.paradise.flickspick.databinding.OttItemBinding

class SelectOttAdapter(
    private val itemClick: (OttModel) -> Unit
) : ListAdapter<OttModel, SelectOttAdapter.SelectOttViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectOttViewHolder {
        val binding = OttItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectOttViewHolder(binding, itemClick)

    }

    override fun onBindViewHolder(holder: SelectOttViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class SelectOttViewHolder(
        private val binding: OttItemBinding,
        private val itemClick: (OttModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OttModel, position: Int) {
            binding.ottContainer.isEnabled = item.isSelected
            binding.ivCheckBox.isVisible = item.isSelected
            binding.tvOttName.text = item.name

            Glide.with(binding.imageView)
                .load(R.drawable.ic_tving)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                val newItem = item.copy(isSelected = item.isSelected.not())
                itemClick(item)
            }
        }
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<OttModel>() {
            override fun areItemsTheSame(oldItem: OttModel, newItem: OttModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OttModel, newItem: OttModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}