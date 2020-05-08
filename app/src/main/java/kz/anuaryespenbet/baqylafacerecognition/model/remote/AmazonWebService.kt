package kz.anuaryespenbet.baqylafacerecognition.model.remote

import io.reactivex.Observable
import kz.anuaryespenbet.baqylafacerecognition.model.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AmazonWebService {
    @POST("/employee")
    fun register(
        @Body data: Map<String, @JvmSuppressWildcards Any>
    ): Observable<ResponseBody>

    @PUT("/recognize/photo")
    fun recognize(
        @Body data: Map<String, @JvmSuppressWildcards Any>
    ): Observable<User>
}