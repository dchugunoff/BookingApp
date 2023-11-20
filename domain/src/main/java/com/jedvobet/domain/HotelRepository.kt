package com.jedvobet.domain

import com.jedvobet.domain.entities.HotelEntity

interface HotelRepository {

    suspend fun getHotelData(): HotelEntity
}