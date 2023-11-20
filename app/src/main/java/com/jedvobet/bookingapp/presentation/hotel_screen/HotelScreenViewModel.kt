package com.jedvobet.bookingapp.presentation.hotel_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedvobet.domain.HotelRepository
import com.jedvobet.domain.entities.HotelEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelScreenViewModel @Inject constructor(private val hotelRepository: HotelRepository) :
    ViewModel() {

    private val _hotel = MutableLiveData<HotelEntity>()
    val hotel: LiveData<HotelEntity> = _hotel

    init {
        getHotelData()
    }

    private fun getHotelData() {
        viewModelScope.launch {
            _hotel.value = hotelRepository.getHotelData()
            Log.d("VIEWMODEL", "${_hotel.value}")
        }
    }
}