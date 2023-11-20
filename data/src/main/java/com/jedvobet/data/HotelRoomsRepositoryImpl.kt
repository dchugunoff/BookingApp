package com.jedvobet.data

import com.jedvobet.data.entities.hotel_rooms_entities.RoomDto
import com.jedvobet.data.network.hotel_rooms.HotelRoomsApiFactory
import com.jedvobet.domain.HotelRoomsRepository
import com.jedvobet.domain.entities.hotel_room_entities.Room
import javax.inject.Inject

class HotelRoomsRepositoryImpl @Inject constructor(private val hotelRoomsApiFactory: HotelRoomsApiFactory) :
    HotelRoomsRepository {

    override suspend fun getHotelRooms(): List<Room> {
        val hotelRooms = hotelRoomsApiFactory.apiService.getHotelRooms()
        return hotelRooms.rooms.map { it.toRoomEntity() }
    }

    private fun RoomDto.toRoomEntity(): Room {
        return Room(
            id = this.id,
            name = this.name,
            image_urls = this.image_urls,
            peculiarities = this.peculiarities,
            price = this.price,
            price_per = this.price_per
        )
    }


}