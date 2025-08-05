package id.assessment.core.di.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoreDispatcher {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}