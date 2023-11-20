package com.jedvobet.bookingapp.presentation.hotel_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.jedvobet.bookingapp.R
import com.jedvobet.bookingapp.databinding.FragmentHotelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding: FragmentHotelBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelBinding == null")

    private val viewModel: HotelScreenViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindHotelData()
    }

    private fun bindHotelData() {
        viewModel.hotel.observe(viewLifecycleOwner) {
            with(binding) {
                /**
                 * Hotel
                 */
                val imageList = it.image_urls.map { SlideModel(it, "") }
                imageCarousel.setImageList(imageList, ScaleTypes.FIT)

                ratingNumber.text = it.rating.toString()
                ratingTitle.text = it.rating_name
                hotelName.text = it.name
                hotelAddress.text = it.adress
                hotelMinimalPrice.text = it.minimal_price.toString()
                hotelMinimalPrice.text =
                    String.format(getString(R.string.minimal_price), it.minimal_price.toString())
                priceTitle.text = it.price_for_it
                /**
                 * About Hotel
                 */
                val adapter = PeculiaritiesAdapter(it.about_the_hotel.peculiarities)
                filtersHotelRv.adapter = adapter
                aboutHotelTitleText.text = it.about_the_hotel.description
                /**
                 * Next screen button
                 */
                val hotelName = it.name
                buttonToChooseRoom.setOnClickListener {
                    navigateToHotelRoomsScreen(hotelName)
                }
            }
        }
    }
    private fun navigateToHotelRoomsScreen(hotelName: String) {
        binding.buttonToChooseRoom.setOnClickListener {
            val action = HotelFragmentDirections.actionHotelFragmentToHotelRoomsFragment(hotelName)
            findNavController().navigate(action)
        }
    }
}