package com.jedvobet.bookingapp.domain.entities.reserve_hotel_room_entity

data class ReserveRoomEntity(
    val arrival_country: String,
    val departure: String,
    val fuel_charge: Int,
    val horating: Int,
    val hotel_adress: String,
    val hotel_name: String,
    val id: Int,
    val number_of_nights: Int,
    val nutrition: String,
    val rating_name: String,
    val room: String,
    val service_charge: Int,
    val tour_date_start: String,
    val tour_date_stop: String,
    val tour_price: Int
)