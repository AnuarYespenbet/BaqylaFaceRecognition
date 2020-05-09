package kz.anuaryespenbet.baqylafacerecognition.view.send_request

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_send_request.*
import kz.anuaryespenbet.baqylafacerecognition.R
import kz.anuaryespenbet.baqylafacerecognition.utils.Status
import kz.anuaryespenbet.baqylafacerecognition.utils.invisible
import kz.anuaryespenbet.baqylafacerecognition.utils.visible

class LoginFragment : SendRequestFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        send_request_btn.text = getString(R.string.send_request_login)
        send_request_btn.setOnClickListener {
            onSendRequestClick()
        }
    }

    private fun onSendRequestClick() {
        sendRequestViewModel.login().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    send_request_btn.invisible()
                    progress_bar.visible()
                }
                Status.SUCCESS -> {
                    sendRequestViewModel.saveName(it.data?.name ?: "username")
                    findNavController().navigate(R.id.action_loginFragment_to_loginSuccessFragment)
                }
                Status.ERROR -> {
                    sendRequestViewModel.saveError(it.error?.message ?: "error")
                    findNavController().navigate(R.id.action_loginFragment_to_loginErrorFragment)
                }
            }
        })
    }
}