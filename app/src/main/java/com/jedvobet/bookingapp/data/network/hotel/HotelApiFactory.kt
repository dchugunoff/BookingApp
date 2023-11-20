package com.jedvobet.bookingapp.data.network.hotel

import com.jedvobet.bookingapp.app.URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HotelApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()

    val apiService = retrofit.create(HotelApiService::class.java)
}