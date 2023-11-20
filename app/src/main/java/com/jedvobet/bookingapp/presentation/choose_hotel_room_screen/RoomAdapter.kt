package com.jedvobet.bookingapp.presentation.choose_hotel_room_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.jedvobet.bookingapp.R
import com.jedvobet.bookingapp.databinding.CardHotelRoomBinding
import com.jedvobet.bookingapp.domain.entities.hotel_room_entities.Room
import com.jedvobet.bookingapp.presentation.hotel_screen.PeculiaritiesAdapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomAdapter @Inject constructor() :
    ListAdapter<Room, RoomAdapter.RoomViewHolder>(DiffCallback) {

    private lateinit var navController: NavController


    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    inner class RoomViewHolder(private val binding: CardHotelRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            with(binding) {
                val imageList = room.image_urls.map { SlideModel(it, "") }
                imageCarousel.setImageList(imageList, ScaleTypes.FIT)
                titleRoom.text = room.name
                roomMinimalPrice.text = "${room.price} ₽"
                priceTitle.text = room.price_per
                buttonToChooseRoom.setOnClickListener {
                    navController.navigate(R.id.action_hotelRoomsFragment_to_reserveHotelRoomFragment)
                }
                setupPeculiaritiesAdapter(room.peculiarities)
            }
        }
        private fun setupPeculiaritiesAdapter(peculiarities: List<String>) {
            val peculiaritiesAdapter = PeculiaritiesAdapter(peculiarities)
            binding.filtersHotelRoomRv.adapter = peculiaritiesAdapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = CardHotelRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = getItem(position)
        holder.bind(room)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Room>() {
            override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}