package com.jedvobet.bookingapp.domain

import com.jedvobet.bookingapp.domain.entities.reserve_hotel_room_entity.ReserveRoomEntity

interface ReserveHotelRoomRepository {

    suspend fun getInfo(): ReserveRoomEntity
}