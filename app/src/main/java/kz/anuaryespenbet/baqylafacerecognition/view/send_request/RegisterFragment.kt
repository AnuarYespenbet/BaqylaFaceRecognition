package kz.anuaryespenbet.baqylafacerecognition.view.send_request

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_send_request.*
import kz.anuaryespenbet.baqylafacerecognition.*
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStoreStringType
import kz.anuaryespenbet.baqylafacerecognition.view.utils.Status
import kz.anuaryespenbet.baqylafacerecognition.view.utils.invisible
import kz.anuaryespenbet.baqylafacerecognition.view.utils.visible

class RegisterFragment : SendRequestFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        send_request_btn.text = getString(R.string.send_request_registration)
        send_request_btn.setOnClickListener {
            sendRequestViewModel.register().observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        send_request_btn.invisible()
                        progress_bar.visible()
                    }
                    Status.SUCCESS -> {
                        sendRequestViewModel.saveName(
                            "${LocalStore()
                                .get(LocalStoreStringType.NAME)} " +
                                    "${LocalStore()
                                        .get(LocalStoreStringType.SURNAME)}"
                        )
                        findNavController().navigate(R.id.action_registerFragment_to_registerSuccessFragment)
                    }
                    Status.ERROR -> {
                        sendRequestViewModel.saveError(it.error?.message ?: "error")
                        findNavController().navigate(R.id.action_registerFragment_to_registerErrorFragment)
                    }
                }
            })
        }
    }
}