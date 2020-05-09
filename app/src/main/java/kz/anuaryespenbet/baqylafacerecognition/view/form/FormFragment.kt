package kz.anuaryespenbet.baqylafacerecognition.view.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_form.*
import kz.anuaryespenbet.baqylafacerecognition.R
import kz.anuaryespenbet.baqylafacerecognition.data.model.User
import kz.anuaryespenbet.baqylafacerecognition.utils.getDayMonthYearWithDots
import kz.anuaryespenbet.baqylafacerecognition.utils.getYearMonthDayWithDash

class FormFragment : Fragment(), TextWatcher {
    private lateinit var formViewModel: FormViewModel
    private var yearMonthDayWithDash = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formViewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        name_edit.addTextChangedListener(this)
        surname_edit.addTextChangedListener(this)
        address_edit.addTextChangedListener(this)
        phone_edit.addTextChangedListener(this)
        date_picker_btn.setOnClickListener {
            openDatePicker()
        }
        next_btn.setOnClickListener {
            onNextClick()
        }
    }

    private fun openDatePicker() {
        val datePickerDialog = DatePickerDialog(requireContext())
        datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
            onDateSelected(datePicker)
        }
        datePickerDialog.show()
    }

    private fun onDateSelected(datePicker: DatePicker) {
        val year = datePicker.year
        val month = datePicker.month
        val day = datePicker.dayOfMonth

        birthday_text.text = getDayMonthYearWithDots(year, month, day)
        birthday_container.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_green)
        yearMonthDayWithDash = getYearMonthDayWithDash(year, month, day)
        isNextEnabled()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        setBgEdit(name_edit)
        setBgEdit(surname_edit)
        setBgEdit(address_edit)
        setBgEdit(phone_edit)
    }

    override fun afterTextChanged(p0: Editable?) {
        isNextEnabled()
    }

    private fun setBgEdit(editText: EditText) {
        editText.background =
            if (editText.text.toString().isEmpty())
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_grey)
            else ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_green)
    }

    private fun isNextEnabled() {
        next_btn.isEnabled = name_edit.text.isNotEmpty()
                && surname_edit.text.isNotEmpty()
                && yearMonthDayWithDash != ""
                && address_edit.text.isNotEmpty()
                && phone_edit.text.isNotEmpty()
    }

    private fun onNextClick() {
        val name = name_edit.text.toString()
        val surname = surname_edit.text.toString()
        val address = address_edit.text.toString()
        val phone = phone_edit.text.toString()
        val user = User(
            name = name,
            surname = surname,
            birthday = yearMonthDayWithDash,
            address = address,
            phone = phone
        )
        formViewModel.saveUser(user)
        findNavController().navigate(R.id.action_formFragment_to_registerTakePhotoFragment)
    }
}