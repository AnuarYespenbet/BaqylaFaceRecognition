package kz.anuaryespenbet.baqylafacerecognition.view.result

import androidx.lifecycle.ViewModel
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreStringType

class ResultViewModel : ViewModel() {
    val username = LocalStore()
        .get(LocalStoreStringType.USERNAME)

    fun clearAll() {
        LocalStore().removeAllValues()
    }
}