package kz.anuaryespenbet.baqylafacerecognition.view.form

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStoreStringType

class FormViewModel : ViewModel() {

    fun setDetails(name: String, surname: String) {
        LocalStore()
            .save(string = name, type = LocalStoreStringType.NAME)
        LocalStore()
            .save(string = surname, type = LocalStoreStringType.SURNAME)
    }
}