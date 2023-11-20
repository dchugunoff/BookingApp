package com.jedvobet.data.network.reserve_hotel_room

import com.jedvobet.data.entities.reserve_hotel_room_entities.ReserveRoomEntityDto
import retrofit2.http.GET

interface ReserveHotelRoomApiService {

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getInfo(): ReserveRoomEntityDto
}