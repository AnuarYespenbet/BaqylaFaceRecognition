package kz.anuaryespenbet.baqylafacerecognition.view.take_photo

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_take_photo.*
import kz.anuaryespenbet.baqylafacerecognition.R

class LoginTakePhotoFragment : TakePhotoFragment(), TakePhotoListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        take_photo_title.text = getString(R.string.take_photo_login_title)
        take_photo_description.text = getString(R.string.take_photo_login_description)
        setListener(this)
    }

    override fun onTakePhoto() {
        findNavController().navigate(R.id.action_loginTakePhotoFragment_to_loginFragment)
    }
}