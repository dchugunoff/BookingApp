package com.jedvobet.domain

import com.jedvobet.domain.entities.hotel_room_entities.Room

interface HotelRoomsRepository {

    suspend fun getHotelRooms(): List<Room>
}