package kz.anuaryespenbet.baqylafacerecognition.model.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import kz.anuaryespenbet.baqylafacerecognition.App

class LocalStore {
    private val preference: SharedPreferences
    private var editor: SharedPreferences.Editor? = null
    private val storeName = "LocalStore"
    private val context =
        App.applicationContext()

    init {
        preference = context.getSharedPreferences(storeName, Context.MODE_PRIVATE)
    }

    fun save(string: String, type: LocalStoreStringType) {
        editor = preference.edit()
        editor?.putString(type.name, string)
        editor?.apply()
    }

    fun get(type: LocalStoreStringType): String? {
        return preference.getString(type.name, null)
    }

    fun <T> save(gsonObject: T, type: LocalStoreObjectType) {
        editor = preference.edit()
        val gson = Gson()
        val jsonString = gson.toJson(gsonObject)
        editor?.putString(type.name, jsonString)
        editor?.apply()
    }

    fun <T> get(type: LocalStoreObjectType, classOfT: Class<T>): T {
        val jsonString = preference.getString(type.name, null)
        val obj = Gson().fromJson<T>(jsonString, classOfT)
        return obj
    }

    private fun removeString(type: LocalStoreStringType) {
        editor = preference.edit()
        editor?.remove(type.name)
        editor?.apply()
    }

    private fun removeObject(type: LocalStoreObjectType) {
        editor = preference.edit()
        editor?.remove(type.name)
        editor?.apply()
    }

    fun removeAllValues() {
        removeString(LocalStoreStringType.NAME)
        removeString(LocalStoreStringType.SURNAME)
        removeString(LocalStoreStringType.PHOTO)
        removeString(LocalStoreStringType.ERROR)
        //removeObject(LocalStoreObjectType.CURRENT_USER)
    }
}

enum class LocalStoreStringType {
    NAME,
    SURNAME,
    PHOTO,
    ERROR,
    USERNAME
}

enum class LocalStoreObjectType {
    CURRENT_USER
}