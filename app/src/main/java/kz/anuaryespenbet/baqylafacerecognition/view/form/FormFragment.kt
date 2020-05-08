package kz.anuaryespenbet.baqylafacerecognition.view.form

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_form.*
import kz.anuaryespenbet.baqylafacerecognition.R

class FormFragment : Fragment(), TextWatcher {
    private lateinit var formViewModel: FormViewModel

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
        name_edit.addTextChangedListener(this)
        surname_edit.addTextChangedListener(this)
        next_btn.setOnClickListener {
            val name = name_edit.text.toString()
            val surname = surname_edit.text.toString()
            formViewModel.setDetails(name, surname)
            findNavController().navigate(R.id.action_formFragment_to_registerTakePhotoFragment)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        setBgEdit(name_edit)
        setBgEdit(surname_edit)
    }

    override fun afterTextChanged(p0: Editable?) {
        next_btn.isEnabled = name_edit.text.isNotEmpty() && surname_edit.text.isNotEmpty()
    }

    private fun setBgEdit(editText: EditText) {
        editText.background =
            if (editText.text.toString().isEmpty())
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_grey)
            else ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_green)
    }
}