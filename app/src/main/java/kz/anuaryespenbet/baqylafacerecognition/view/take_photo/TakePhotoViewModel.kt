package kz.anuaryespenbet.baqylafacerecognition.view.take_photo

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStoreStringType

class TakePhotoViewModel : ViewModel() {
    fun setPhoto(photo: String) {
        LocalStore()
            .save(photo, LocalStoreStringType.PHOTO)
    }
}