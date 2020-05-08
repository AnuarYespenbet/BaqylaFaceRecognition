package kz.anuaryespenbet.baqylafacerecognition.model.remote

val client = NetworkClient()

object NetworkService {
    private val service = client.getRetrofit()

    fun createApiService(): AmazonWebService {
        return service.create(AmazonWebService::class.java)
    }
}