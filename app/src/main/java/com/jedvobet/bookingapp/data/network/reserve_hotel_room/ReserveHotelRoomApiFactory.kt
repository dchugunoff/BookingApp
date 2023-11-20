package com.jedvobet.bookingapp.data.network.reserve_hotel_room

import com.jedvobet.bookingapp.app.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReserveHotelRoomApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()

    val apiService = retrofit.create(ReserveHotelRoomApiService::class.java)
}