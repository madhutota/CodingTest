package com.madhu.codingtest.presentation.dispatchers

import com.madhu.codingtest.util.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcherProvider(private val testDispatcher: CoroutineDispatcher) : DispatcherProvider {
    override val io: CoroutineDispatcher = testDispatcher
    override val default: CoroutineDispatcher = testDispatcher
    override val main: CoroutineDispatcher = testDispatcher
}