package com.hqapps.domain.interactor

import com.hqapps.domain.repository.RemoteSearchRepository
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
    private lateinit var mockRemoteSearchRepository: RemoteSearchRepository

    @Before
    fun setUp() {
        remoteSearchUseCase = RemoteSearchUseCase(mockRemoteSearchRepository)
    }

    @Test
    fun testRemoteSearchUseCaseInteraction() {
        remoteSearchUseCase.buildUseCaseObservable("hehe")
        Mockito.verify(mockRemoteSearchRepository)?.searchRemote("hehe")
        Mockito.verifyNoMoreInteractions(mockRemoteSearchRepository)
    }
}
