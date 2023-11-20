package com.jedvobet.bookingapp.data

import com.jedvobet.bookingapp.data.entities.reserve_hotel_room_entities.ReserveRoomEntityDto
import com.jedvobet.bookingapp.data.network.reserve_hotel_room.ReserveHotelRoomApiFactory
import com.jedvobet.bookingapp.domain.ReserveHotelRoomRepository
import com.jedvobet.bookingapp.domain.entities.reserve_hotel_room_entity.ReserveRoomEntity
import javax.inject.Inject

class ReserveHotelRoomRepositoryImpl @Inject constructor(private val reserveHotelRoomFactory: ReserveHotelRoomApiFactory) :
    ReserveHotelRoomRepository {
    override suspend fun getInfo(): ReserveRoomEntity {
        return reserveHotelRoomFactory.apiService.getInfo().toEntity()
    }

    private fun ReserveRoomEntityDto.toEntity(): ReserveRoomEntity {
        return ReserveRoomEntity(
            arrival_country = this.arrival_country,
            departure = this.departure,
            fuel_charge = this.fuel_charge,
            horating = this.horating,
            hotel_adress = this.hotel_adress,
            hotel_name = this.hotel_name,
            id = this.id,
            number_of_nights = this.number_of_nights,
            nutrition = this.nutrition,
            rating_name = this.rating_name,
            room = this.room,
            service_charge = this.service_charge,
            tour_date_start = this.tour_date_start,
            tour_date_stop = this.tour_date_stop,
            tour_price = this.tour_price,
        )
    }
}