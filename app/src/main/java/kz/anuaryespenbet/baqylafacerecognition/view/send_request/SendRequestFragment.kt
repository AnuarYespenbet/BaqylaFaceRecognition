package kz.anuaryespenbet.baqylafacerecognition.view.send_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kz.anuaryespenbet.baqylafacerecognition.R

open class SendRequestFragment : Fragment() {
    lateinit var sendRequestViewModel: SendRequestViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendRequestViewModel = ViewModelProvider(this).get(SendRequestViewModel::class.java)
    }
}