package com.jedvobet.data.network.hotel_rooms

import com.jedvobet.data.entities.hotel_rooms_entities.RoomsDto
import retrofit2.http.GET

interface HotelRoomsApiService {

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getHotelRooms(): RoomsDto
}