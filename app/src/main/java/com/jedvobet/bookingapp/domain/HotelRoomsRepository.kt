package com.jedvobet.bookingapp.domain

import com.jedvobet.bookingapp.domain.entities.hotel_room_entities.Room

interface HotelRoomsRepository {

    suspend fun getHotelRooms(): List<Room>
}