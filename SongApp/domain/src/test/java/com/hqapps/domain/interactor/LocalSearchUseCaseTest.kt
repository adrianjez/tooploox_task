package com.hqapps.domain.interactor

import com.hqapps.domain.repository.SearchRepository
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
    private lateinit var mockSearchRepository: SearchRepository

    @Before
    fun setUp(){
        localSearchUseCase = LocalSearchUseCase(mockSearchRepository)
    }

    @Test
    fun test1(){
        localSearchUseCase.buildUseCaseObservable("hehe")
        verify(mockSearchRepository)?.searchLocal("hehe")
        verifyNoMoreInteractions(mockSearchRepository)
    }
}