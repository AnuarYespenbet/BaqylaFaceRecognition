package kz.anuaryespenbet.baqylafacerecognition.view.result

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_result.*
import kz.anuaryespenbet.baqylafacerecognition.R

class LoginSuccessFragment : ResultFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val helloUser = "Привет, ${resultViewModel.username}!"
        result_title.text = helloUser
        result_description.text = getString(R.string.result_login_success)
    }
}