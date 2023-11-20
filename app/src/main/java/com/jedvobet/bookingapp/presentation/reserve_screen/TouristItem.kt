package com.jedvobet.bookingapp.presentation.reserve_screen

data class TouristItem(
    val title: String,
    var isExpandable: Boolean = false,
    val name: String?,
    val surname: String?,
    val date: String?,
    val citizenship: String?,
    val passport: String?,
    val datePassport: String?
)
