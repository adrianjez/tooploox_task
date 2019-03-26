package com.hqapps.domain.interactor

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.LocalSearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class LocalSearchUseCase : BaseUseCase<ListResponse<SearchEntity>, String> {

    private val repo: LocalSearchRepository

    @Inject
    constructor(repository: LocalSearchRepository) {
        this.repo = repository
    }

    override fun buildUseCaseObservable(params: String): Observable<ListResponse<SearchEntity>> {
        return repo.searchLocal(params)
    }

}