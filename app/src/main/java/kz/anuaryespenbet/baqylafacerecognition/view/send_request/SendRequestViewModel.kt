package kz.anuaryespenbet.baqylafacerecognition.view.send_request

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kz.anuaryespenbet.baqylafacerecognition.view.base.BaseViewModel
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStore
import kz.anuaryespenbet.baqylafacerecognition.model.local.LocalStoreStringType
import kz.anuaryespenbet.baqylafacerecognition.model.User
import kz.anuaryespenbet.baqylafacerecognition.model.UserRepository
import kz.anuaryespenbet.baqylafacerecognition.model.remote.NetworkService
import kz.anuaryespenbet.baqylafacerecognition.view.utils.Event
import okhttp3.ResponseBody
import timber.log.Timber

class SendRequestViewModel : BaseViewModel() {
    private val networkService =
        NetworkService
    private val api = networkService.createApiService()
    private val repository =
        UserRepository(api)
    private val photo = LocalStore()
        .get(LocalStoreStringType.PHOTO)

    fun saveError(error: String) {
        LocalStore()
            .save(error, LocalStoreStringType.ERROR)
    }

    fun saveName(username: String) {
        LocalStore()
            .save(username, LocalStoreStringType.USERNAME)
    }

    fun login(): MutableLiveData<Event<User>> {
        val data = HashMap<String, Any>()
        photo?.let { data["photoData"] = it }

        val liveData = MutableLiveData<Event<User>>()

        liveData.postValue(Event.loading())
        val observer: DisposableObserver<User> = object : DisposableObserver<User>() {
            override fun onComplete() {
                Timber.d("completed")
            }

            override fun onNext(t: User) {
                liveData.postValue(Event.success(t))
            }

            override fun onError(e: Throwable) {
                liveData.postValue(Event.error(e))
            }
        }

        compositeDisposable.add(
            repository.recognize(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        )
        return liveData
    }

    fun register(): MutableLiveData<Event<ResponseBody>> {
        val name = LocalStore()
            .get(LocalStoreStringType.NAME)
        val surname = LocalStore()
            .get(LocalStoreStringType.SURNAME)
        val data = HashMap<String, Any>()
        val username = "$name $surname"
        data["name"] = username
        photo?.let { data["photo"] = photo }

        val liveData = MutableLiveData<Event<ResponseBody>>()
        liveData.postValue(Event.loading())
        val observer: DisposableObserver<ResponseBody> =
            object : DisposableObserver<ResponseBody>() {
                override fun onComplete() {
                    Timber.d("completed")
                }

                override fun onNext(t: ResponseBody) {
                    liveData.postValue(Event.success(t))
                }

                override fun onError(e: Throwable) {
                    liveData.postValue(Event.error(e))
                }
            }

        compositeDisposable.add(
            repository.register(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        )
        return liveData
    }
}