package com.hqapps.domain.interactor

import com.hqapps.domain.repository.SearchRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteSearchUseCaseTest {

    private lateinit var remoteSearchUseCase: RemoteSearchUseCase

    @Mock
    private lateinit var mockSearchRepository: SearchRepository

    @Before
    fun setUp() {
        remoteSearchUseCase = RemoteSearchUseCase(mockSearchRepository)
    }

    @Test
    fun test1() {
        remoteSearchUseCase.buildUseCaseObservable("hehe")
        Mockito.verify(mockSearchRepository)?.searchLocal("hehe")
        Mockito.verifyNoMoreInteractions(mockSearchRepository)
    }
}
