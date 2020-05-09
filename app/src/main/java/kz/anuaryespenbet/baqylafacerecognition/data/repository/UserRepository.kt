package kz.anuaryespenbet.baqylafacerecognition.data.repository

import io.reactivex.Observable
import kz.anuaryespenbet.baqylafacerecognition.data.model.User
import kz.anuaryespenbet.baqylafacerecognition.data.remote.AmazonWebService
import okhttp3.ResponseBody

class UserRepository(private val webService: AmazonWebService) {

    fun recognize(data: HashMap<String, Any>): Observable<User> {
        return webService.recognize(data)
    }

    fun register(data: HashMap<String, Any>): Observable<ResponseBody> {
        return webService.register(data)
    }
}