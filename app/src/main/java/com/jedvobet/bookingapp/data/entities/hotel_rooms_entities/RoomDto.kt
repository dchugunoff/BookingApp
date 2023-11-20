package com.jedvobet.bookingapp.data.entities.hotel_rooms_entities

data class RoomDto(
    val id: Int,
    val image_urls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val price_per: String
)