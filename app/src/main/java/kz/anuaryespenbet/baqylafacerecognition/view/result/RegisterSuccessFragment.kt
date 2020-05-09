package kz.anuaryespenbet.baqylafacerecognition.view.result

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_result.*
import kz.anuaryespenbet.baqylafacerecognition.R

class RegisterSuccessFragment : ResultFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val helloUser = "Привет, ${resultViewModel.user.name}!"
        result_title.text = helloUser
        result_description.text = getString(R.string.result_register_success)
    }
}