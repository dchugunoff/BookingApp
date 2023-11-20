package com.jedvobet.bookingapp.domain

import com.jedvobet.bookingapp.domain.entities.HotelEntity

interface HotelRepository {

    suspend fun getHotelData(): HotelEntity
}