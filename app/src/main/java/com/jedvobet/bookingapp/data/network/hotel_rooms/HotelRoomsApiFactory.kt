package com.jedvobet.bookingapp.data.network.hotel_rooms

import com.jedvobet.bookingapp.app.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HotelRoomsApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()

    val apiService = retrofit.create(HotelRoomsApiService::class.java)
}