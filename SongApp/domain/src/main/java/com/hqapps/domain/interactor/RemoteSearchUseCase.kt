package com.hqapps.domain.interactor

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class RemoteSearchUseCase : BaseUseCase<ListResponse<SearchEntity>, String> {

    private val repo: SearchRepository

    @Inject
    constructor(repository: SearchRepository) {
        this.repo = repository
    }

    override fun buildUseCaseObservable(params: String): Observable<ListResponse<SearchEntity>> {
        return repo.searchRemote(params)
    }

}