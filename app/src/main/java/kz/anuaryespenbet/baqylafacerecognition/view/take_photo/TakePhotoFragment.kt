package kz.anuaryespenbet.baqylafacerecognition.view.take_photo

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_take_photo.*
import kz.anuaryespenbet.baqylafacerecognition.R
import java.io.ByteArrayOutputStream

open class TakePhotoFragment : Fragment() {
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    private lateinit var takePhotoViewModel: TakePhotoViewModel

    private var takePhotoListener: TakePhotoListener? = null
    fun setListener(listener: TakePhotoListener) {
        takePhotoListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_take_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        takePhotoViewModel = ViewModelProvider(this).get(TakePhotoViewModel::class.java)

        take_photo_btn.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    startActivityForResult(
                        takePictureIntent,
                        REQUEST_IMAGE_CAPTURE
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val encodedImage = Base64.encodeToString(byteArray, Base64.NO_WRAP)
            takePhotoViewModel.setPhoto(encodedImage)

            takePhotoListener?.onTakePhoto()
            //findNavController().navigate(R.id.action_takePhotoFragment_to_sendRequestFragment)
        }
    }
}