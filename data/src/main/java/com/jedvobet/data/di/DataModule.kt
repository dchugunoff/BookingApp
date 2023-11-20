package com.jedvobet.data.di

import com.jedvobet.data.HotelRepositoryImpl
import com.jedvobet.data.HotelRoomsRepositoryImpl
import com.jedvobet.data.ReserveHotelRoomRepositoryImpl
import com.jedvobet.data.network.hotel.HotelApiFactory
import com.jedvobet.data.network.hotel_rooms.HotelRoomsApiFactory
import com.jedvobet.data.network.reserve_hotel_room.ReserveHotelRoomApiFactory
import com.jedvobet.domain.HotelRepository
import com.jedvobet.domain.HotelRoomsRepository
import com.jedvobet.domain.ReserveHotelRoomRepository
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