package com.jedvobet.bookingapp.presentation.reserve_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jedvobet.bookingapp.R
import com.jedvobet.bookingapp.databinding.FragmentReserveHotelRoomBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import javax.inject.Inject

@AndroidEntryPoint
class ReserveHotelRoomFragment : Fragment() {

    private var _binding: FragmentReserveHotelRoomBinding? = null
    private val binding: FragmentReserveHotelRoomBinding
        get() = _binding ?: throw RuntimeException("FragmentReserveHotelRoomBinding == null")

    private val viewModel: ReserveScreenViewModel by viewModels()

    @Inject
    lateinit var adapter: TouristAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReserveHotelRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        bindData()
        bindPhoneMask()

    }

    private fun setupAdapter() {
        binding.touristRv.adapter = adapter
        viewModel.touristList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun bindPhoneMask() {
        val slots = PhoneNumberUnderscoreSlotsParser().parseSlots("+7 (___) ___-__-__")
        val mask = MaskImpl.createTerminated(slots)
        mask.placeholder = '*'
        mask.isShowingEmptySlots = true
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.phoneEdittext)
    }

    private fun isValidPhoneNumber(): Boolean {
        return if (binding.phoneEdittext.text.toString()
                .contains('*') || binding.phoneEdittext.text.toString().length < 11
        ) {
            Log.d("PHONE", binding.phoneEdittext.text.toString())
            binding.phoneEdittext.error = "Введите корректный номер телефона"
            return false
        } else {
            Log.d("PHONE", binding.phoneEdittext.text.toString())
            true
        }
    }

    private fun isValidEmail(): Boolean {
        return if (binding.emailEdittext.isEmailValid()) {
            true
        } else {
            binding.emailEdittext.error = "Введите корректную электронную почту"
            false
        }
    }

    private fun EditText.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

    private fun bindData() {
        viewModel.reserveData.observe(viewLifecycleOwner) {
            with(binding) {
                /**
                 * Information
                 */
                ratingNumber.text = it.horating.toString()
                ratingTitle.text = it.rating_name
                hotelName.text = it.hotel_name
                hotelAddress.text = it.hotel_adress
                departure.text = it.departure
                country.text = it.arrival_country
                date.text = String.format(
                    getString(R.string.date_tour_time),
                    it.tour_date_start,
                    it.tour_date_stop
                )
                countNights.text = it.number_of_nights.toString()
                hotel.text = it.hotel_name
                room.text = it.room
                meals.text = it.nutrition
                /**
                 * Price information
                 */
                tourPrice.text = String.format(getString(R.string.price), it.tour_price.toString())
                fuelPrice.text = String.format(getString(R.string.price), it.fuel_charge.toString())
                servicePrice.text =
                    String.format(getString(R.string.price), it.service_charge.toString())
            }
        }
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice.text = String.format(getString(R.string.price), it.toString())
            binding.buyButton.text = String.format(getString(R.string.price), it.toString())
        }
        binding.expandableIcon.setOnClickListener {
            viewModel.addTourist()
        }
        binding.screenToolbar.apply {
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.buyButton.setOnClickListener {
            isValidPhoneNumber()
            isValidEmail()
            if (isValidPhoneNumber() && isValidEmail() && isFirstTouristDataFilled()) {
                Log.d("PHONE", "${isValidPhoneNumber()}")
                findNavController().navigate(R.id.action_reserveHotelRoomFragment_to_orderAcceptedFragment)
            }
        }
    }

    private fun isFirstTouristDataFilled(): Boolean {
        val firstItemPosition = 0
        val firstItemView = binding.touristRv.layoutManager?.findViewByPosition(firstItemPosition)

        if (firstItemView != null) {
            val nameEditText = firstItemView.findViewById<EditText>(R.id.name_edittext)
            val surnameEditText = firstItemView.findViewById<EditText>(R.id.surname_edittext)
            val dateEditText = firstItemView.findViewById<EditText>(R.id.date_edittext)
            val citizenshipEditText = firstItemView.findViewById<EditText>(R.id.citizenship_edittext)
            val passportNumberEditText = firstItemView.findViewById<EditText>(R.id.passport_edittext)
            val passportExpirationEditText = firstItemView.findViewById<EditText>(R.id.passport_expiration_edittext)

            return areFieldsFilled(
                nameEditText.text.toString(),
                surnameEditText.text.toString(),
                dateEditText.text.toString(),
                citizenshipEditText.text.toString(),
                passportNumberEditText.text.toString(),
                passportExpirationEditText.text.toString()
            )
        }

        return false
    }
    private fun areFieldsFilled(
        name: String,
        surname: String,
        dateOfBirth: String,
        citizenship: String,
        passportNumber: String,
        passportExpiration: String
    ): Boolean {
        return name.isNotBlank() &&
                surname.isNotBlank() &&
                dateOfBirth.isNotBlank() &&
                citizenship.isNotBlank() &&
                passportNumber.isNotBlank() &&
                passportExpiration.isNotBlank()
    }
}