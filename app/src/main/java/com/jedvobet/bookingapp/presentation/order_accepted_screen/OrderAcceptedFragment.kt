package com.jedvobet.bookingapp.presentation.order_accepted_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jedvobet.bookingapp.R
import com.jedvobet.bookingapp.databinding.FragmentOrderAcceptedBinding

class OrderAcceptedFragment : Fragment() {

    private var _binding: FragmentOrderAcceptedBinding? = null
    private val binding: FragmentOrderAcceptedBinding
        get() = _binding ?: throw RuntimeException("FragmentOrderAcceptedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderAcceptedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenToolbar.apply {
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.superButton.setOnClickListener {
            findNavController().popBackStack(R.id.hotelFragment, false)
        }
    }
}