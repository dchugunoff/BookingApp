package com.jedvobet.bookingapp.data.di

import com.jedvobet.bookingapp.data.ReserveHotelRoomRepositoryImpl
import com.jedvobet.bookingapp.data.network.HotelRepositoryImpl
import com.jedvobet.bookingapp.data.network.HotelRoomsRepositoryImpl
import com.jedvobet.bookingapp.data.network.hotel.HotelApiFactory
import com.jedvobet.bookingapp.data.network.hotel_rooms.HotelRoomsApiFactory
import com.jedvobet.bookingapp.data.network.reserve_hotel_room.ReserveHotelRoomApiFactory
import com.jedvobet.bookingapp.domain.HotelRepository
import com.jedvobet.bookingapp.domain.HotelRoomsRepository
import com.jedvobet.bookingapp.domain.ReserveHotelRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideHotelRepositoryImpl(): HotelRepository {
        return HotelRepositoryImpl(HotelApiFactory)
    }

    @Singleton
    @Provides
    fun provideHotelRoomsRepository(): HotelRoomsRepository {
        return HotelRoomsRepositoryImpl(HotelRoomsApiFactory)
    }

    @Singleton
    @Provides
    fun provideReserveHotelRoomRepository(): ReserveHotelRoomRepository {
        return ReserveHotelRoomRepositoryImpl(ReserveHotelRoomApiFactory)
    }
}