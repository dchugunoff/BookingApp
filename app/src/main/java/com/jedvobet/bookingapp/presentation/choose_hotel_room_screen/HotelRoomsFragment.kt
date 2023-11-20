package com.jedvobet.bookingapp.presentation.choose_hotel_room_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jedvobet.bookingapp.databinding.FragmentHotelRoomsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HotelRoomsFragment : Fragment() {

    private var _binding: FragmentHotelRoomsBinding? = null
    private val binding: FragmentHotelRoomsBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelRoomsBinding")

    private val viewModel: HotelRoomsViewModel by viewModels()

    @Inject
    lateinit var adapter: RoomAdapter

    private val args: HotelRoomsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindHotelRoomsData()
        binding.screenToolbar.apply {
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun bindHotelRoomsData() {
        adapter.setNavController(findNavController())
        viewModel.hotelRoomsList.observe(viewLifecycleOwner) {
            binding.titleToolbar.text = args.hotelName
            binding.rvHotelRooms.adapter = adapter
            adapter.submitList(it)
        }
    }


}