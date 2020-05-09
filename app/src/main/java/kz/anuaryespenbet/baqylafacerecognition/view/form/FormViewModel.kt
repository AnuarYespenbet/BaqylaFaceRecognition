package kz.anuaryespenbet.baqylafacerecognition.view.form

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreObjectType
import kz.anuaryespenbet.baqylafacerecognition.data.model.User

class FormViewModel : ViewModel() {

    fun saveUser(user: User) {
        LocalStore().save(user, LocalStoreObjectType.CURRENT_USER)
    }
}