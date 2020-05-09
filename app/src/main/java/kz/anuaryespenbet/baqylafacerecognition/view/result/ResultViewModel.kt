package kz.anuaryespenbet.baqylafacerecognition.view.result

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreObjectType
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreStringType
import kz.anuaryespenbet.baqylafacerecognition.data.model.User

class ResultViewModel : ViewModel() {
    val username = LocalStore()
        .get(LocalStoreStringType.NAME)

    val user = LocalStore().get(LocalStoreObjectType.CURRENT_USER, User::class.java)

    fun clearAll() {
        LocalStore().removeAllValues()
    }
}