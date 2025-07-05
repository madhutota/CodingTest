package com.madhu.codingtest.util

sealed interface UIState<out T> {
    data class Success<T>(val data: T) : UIState<T>
    data class Failure<T>(val throwable: Throwable? = null, val data: T? = null) : UIState<T>
    data object Loading : UIState<Nothing>
}