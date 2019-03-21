package com.hqapps.domain.interactor

import io.reactivex.Observable


abstract class BaseUseCase<T, Params> {
    abstract fun buildUseCaseObservable(params: Params): Observable<T>
}