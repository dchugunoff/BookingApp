package com.jedvobet.bookingapp.presentation.reserve_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jedvobet.bookingapp.domain.ReserveHotelRoomRepository
import com.jedvobet.bookingapp.domain.entities.reserve_hotel_room_entity.ReserveRoomEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveScreenViewModel @Inject constructor(private val reserveRepository: ReserveHotelRoomRepository) :
    ViewModel() {

    private val _reserveData = MutableLiveData<ReserveRoomEntity>()
    val reserveData: LiveData<ReserveRoomEntity> = _reserveData

    private val _touristList = MutableLiveData<List<TouristItem>>()
    val touristList: LiveData<List<TouristItem>> = _touristList

    private val _totalPrice = MutableLiveData<Int>()
    val totalPrice: LiveData<Int> = _totalPrice

    init {
        addTourist()
        getReserveData()
    }

    private fun getReserveData() {
        viewModelScope.launch {
            _reserveData.value = reserveRepository.getInfo()
            sumTotalPrice()
        }
    }

    fun addTourist() {
        val currentList = _touristList.value ?: emptyList()
        if (currentList.size <= 3) {
            val titleForTourist = when (currentList.size) {
                0 -> "Первый турист"
                1 -> "Второй турист"
                2 -> "Третий турист"
                else -> "Четвертый турист"
            }
            val updatedList = currentList + TouristItem(
                title = titleForTourist,
                isExpandable = false,
                name = null,
                surname = null,
                passport = null,
                date = null,
                datePassport = null,
                citizenship = null
            )
            _touristList.value = updatedList
        }
    }

    private fun sumTotalPrice() {
        viewModelScope.launch {
            if (_reserveData.value != null) {
                val totalPrice =
                    _reserveData.value?.let { it.tour_price + it.service_charge + it.fuel_charge }
                _totalPrice.value = totalPrice ?: 0
            }
        }
    }
}