package com.hqapps.domain.interactor

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.RemoteSearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class RemoteSearchUseCase : BaseUseCase<ListResponse<SearchEntity>, String> {

    private val repo: RemoteSearchRepository

    @Inject
    constructor(repositoryRemote: RemoteSearchRepository) {
        this.repo = repositoryRemote
    }

    override fun buildUseCaseObservable(params: String): Observable<ListResponse<SearchEntity>> {
        return repo.searchRemote(params)
    }

}