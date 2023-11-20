package com.jedvobet.bookingapp.presentation.choose_hotel_room_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedvobet.bookingapp.domain.HotelRoomsRepository
import com.jedvobet.bookingapp.domain.entities.hotel_room_entities.Room
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelRoomsViewModel @Inject constructor(private val repository: HotelRoomsRepository) :
    ViewModel() {

    private val _hotelRoomsList = MutableLiveData<List<Room>>()
    val hotelRoomsList: LiveData<List<Room>> = _hotelRoomsList

    init {
        getHotelRooms()
    }

    fun getHotelRooms() {
        viewModelScope.launch {
            _hotelRoomsList.value = repository.getHotelRooms()
        }
    }

}