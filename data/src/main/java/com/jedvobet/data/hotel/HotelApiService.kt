package com.jedvobet.data.network.hotel

import com.jedvobet.data.entities.hotel_entities.HotelEntityDto
import retrofit2.http.GET

interface HotelApiService {

    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelEntityDto
}