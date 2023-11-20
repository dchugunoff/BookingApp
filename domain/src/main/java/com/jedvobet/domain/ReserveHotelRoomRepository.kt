package com.jedvobet.domain

import com.jedvobet.domain.entities.reserve_hotel_room_entity.ReserveRoomEntity

interface ReserveHotelRoomRepository {

    suspend fun getInfo(): ReserveRoomEntity
}