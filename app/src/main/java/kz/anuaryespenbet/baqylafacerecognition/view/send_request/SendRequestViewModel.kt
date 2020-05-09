package kz.anuaryespenbet.baqylafacerecognition.view.send_request

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreObjectType
import kz.anuaryespenbet.baqylafacerecognition.data.local.LocalStoreStringType
import kz.anuaryespenbet.baqylafacerecognition.data.model.User
import kz.anuaryespenbet.baqylafacerecognition.data.remote.NetworkService
import kz.anuaryespenbet.baqylafacerecognition.data.repository.UserRepository
import kz.anuaryespenbet.baqylafacerecognition.utils.Event
import kz.anuaryespenbet.baqylafacerecognition.view.base.BaseViewModel
import okhttp3.ResponseBody

class SendRequestViewModel : BaseViewModel() {
    private val networkService = NetworkService
    private val api = networkService.createApiService()
    private val repository = UserRepository(api)
    private val photo = LocalStore()
        .get(LocalStoreStringType.PHOTO)

    fun saveError(error: String) {
        LocalStore().save(error, LocalStoreStringType.ERROR)
    }

    fun saveName(username: String) {
        LocalStore().save(username, LocalStoreStringType.NAME)
    }

    fun login(): MutableLiveData<Event<User>> {
        val data = HashMap<String, Any>()
        photo?.let { data["photoData"] = it }

        val liveData = MutableLiveData<Event<User>>()
        addDisposable(
            repository.recognize(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.postValue(Event.loading()) }
                .subscribe(
                    { liveData.postValue(Event.success(it)) },
                    { liveData.postValue(Event.error(it)) }
                )
        )
        return liveData
    }

    fun register(): MutableLiveData<Event<ResponseBody>> {
        val user = LocalStore().get(LocalStoreObjectType.CURRENT_USER, User::class.java)
        val data = HashMap<String, Any>()

        data["name"] = user.name as String
        data["surname"] = user.surname as String
        data["birthday"] = user.birthday as String
        data["address"] = user.address as String
        data["telephone"] = user.phone as String

        photo?.let { data["photo"] = photo }

        val liveData = MutableLiveData<Event<ResponseBody>>()
        addDisposable(
            repository.register(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveData.postValue(Event.loading()) }
                .subscribe(
                    { liveData.postValue(Event.success(it)) },
                    { liveData.postValue(Event.error(it)) }
                )
        )
        return liveData
    }
}