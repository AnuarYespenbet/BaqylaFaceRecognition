package kz.anuaryespenbet.baqylafacerecognition.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun addDisposable(d: Disposable) {
        compositeDisposable.add(d)
    }
}