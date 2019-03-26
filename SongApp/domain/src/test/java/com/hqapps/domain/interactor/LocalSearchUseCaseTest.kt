package com.hqapps.domain.interactor

import com.hqapps.domain.repository.LocalSearchRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalSearchUseCaseTest {

    private lateinit var localSearchUseCase: LocalSearchUseCase

    @Mock
    private lateinit var mockLocalSearchRepository: LocalSearchRepository

    @Before
    fun setUp(){
        localSearchUseCase = LocalSearchUseCase(mockLocalSearchRepository)
    }

    @Test
    fun testLocalSearchUseCaseInteraction(){
        localSearchUseCase.buildUseCaseObservable("hehe")
        verify(mockLocalSearchRepository)?.searchLocal("hehe")
        verifyNoMoreInteractions(mockLocalSearchRepository)
    }
}