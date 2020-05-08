package kz.anuaryespenbet.baqylafacerecognition.view.result

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_result.*
import kz.anuaryespenbet.baqylafacerecognition.R

class LoginErrorFragment : ResultFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result_title.text = getString(R.string.result_error)
        result_description.text = getString(R.string.result_error_not_found)
    }
}