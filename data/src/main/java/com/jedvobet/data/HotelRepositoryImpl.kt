package com.jedvobet.data

import com.jedvobet.data.entities.hotel_entities.AboutTheHotelDto
import com.jedvobet.data.entities.hotel_entities.HotelEntityDto
import com.jedvobet.data.network.hotel.HotelApiFactory
import com.jedvobet.domain.HotelRepository
import com.jedvobet.domain.entities.AboutTheHotel
import com.jedvobet.domain.entities.HotelEntity
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(private val hotelApiFactory: HotelApiFactory) :
    HotelRepository {

    override suspend fun getHotelData(): HotelEntity {
        return hotelApiFactory.apiService.getHotel().toHotelEntity()
    }

    private fun HotelEntityDto.toHotelEntity(): HotelEntity {
        return HotelEntity(
            about_the_hotel = this.about_the_hotel.toAboutTheHotel(),
            adress = this.adress,
            id = this.id,
            image_urls = this.image_urls,
            minimal_price = this.minimal_price,
            name = this.name,
            price_for_it = this.price_for_it,
            rating = this.rating,
            rating_name = this.rating_name
        )
    }

    private fun AboutTheHotelDto.toAboutTheHotel(): AboutTheHotel {
        return AboutTheHotel(
            description = this.description,
            peculiarities = this.peculiarities
        )
    }
}