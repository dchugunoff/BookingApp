package com.jedvobet.bookingapp.presentation.reserve_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jedvobet.bookingapp.R
import com.jedvobet.bookingapp.databinding.CardTouristBinding
import javax.inject.Inject

class TouristAdapter @Inject constructor() :
    ListAdapter<TouristItem, TouristAdapter.TouristViewHolder>(DiffCallback) {

    inner class TouristViewHolder(private val binding: CardTouristBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tourist: TouristItem) {
            with(binding) {
                touristTitle.text = tourist.title
                val isExpandable = tourist.isExpandable
                touristData.visibility = if (isExpandable) View.VISIBLE else View.GONE
                expandableIcon.setImageDrawable(
                    if (isExpandable) {
                        ContextCompat.getDrawable(expandableIcon.context, R.drawable.arrow_up)
                    } else {
                        ContextCompat.getDrawable(
                            expandableIcon.context,
                            R.drawable.arrow_down_icon
                        )
                    }
                )
                expandableIcon.setOnClickListener {
                    tourist.isExpandable = !tourist.isExpandable
                    notifyItemChanged(adapterPosition)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val binding = CardTouristBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val tourist = getItem(position)
        holder.bind(tourist)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<TouristItem>() {
            override fun areItemsTheSame(oldItem: TouristItem, newItem: TouristItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TouristItem, newItem: TouristItem): Boolean {
                return oldItem.title == newItem.title && oldItem.isExpandable == newItem.isExpandable
            }

        }
    }

}