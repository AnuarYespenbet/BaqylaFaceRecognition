package kz.anuaryespenbet.baqylafacerecognition.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("surname") val surname: String? = null,
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("address") val address: String? = null
)