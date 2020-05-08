package kz.anuaryespenbet.baqylafacerecognition.view.result

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStoreStringType

class ResultViewModel : ViewModel() {
    val username = LocalStore()
        .get(LocalStoreStringType.USERNAME)

    fun clearAll() {
        LocalStore().removeAllValues()
    }
}