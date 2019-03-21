package com.hqapps.sample_app.presentation.search.remote_search

import androidx.lifecycle.MutableLiveData
import com.hqapps.domain.interactor.RemoteSearchUseCase
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.sample_app.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteSearchViewModel : BaseViewModel {

    private val disposables = CompositeDisposable()

    private val remoteSearchUseCase: RemoteSearchUseCase

    var response: MutableLiveData<ListResponse<SearchEntity>> = MutableLiveData()
        private set

    var errorLiveData: MutableLiveData<String> = MutableLiveData()
        private set

    var showHideProgression: MutableLiveData<Boolean> = MutableLiveData()
        private set

    @Inject
    constructor(useCase: RemoteSearchUseCase) {
        this.remoteSearchUseCase = useCase
    }

    override fun onCleared() {
        super.onCleared()
        this.disposables.clear()
    }

    fun searchDataFor(query: String) {
        showHideProgression.postValue(true)
        disposables.add(remoteSearchUseCase.buildUseCaseObservable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resp ->
                    response.postValue(resp)
                    showHideProgression.postValue(false)
                }, { error ->
                    errorLiveData.postValue(error.message)
                    showHideProgression.postValue(false)
                })
        )
    }
}