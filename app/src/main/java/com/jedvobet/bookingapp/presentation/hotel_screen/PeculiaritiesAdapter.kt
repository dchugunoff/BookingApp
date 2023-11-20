package com.jedvobet.bookingapp.presentation.hotel_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jedvobet.bookingapp.R

class PeculiaritiesAdapter(private val peculiarities: List<String>) :
    RecyclerView.Adapter<PeculiaritiesAdapter.PeculiaritiesViewHolder>() {

    class PeculiaritiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.filter_string)

        fun bind(peculiarity: String) {
            textView.text = peculiarity
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeculiaritiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_filters, parent, false)
        return PeculiaritiesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PeculiaritiesViewHolder,
        position: Int
    ) {
        holder.bind(peculiarities[position])
    }

    override fun getItemCount(): Int {
        return peculiarities.size
    }
}