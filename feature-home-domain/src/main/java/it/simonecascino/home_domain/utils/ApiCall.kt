package it.simonecascino.home_domain.utils

sealed interface ApiStatus{
    object Idle: ApiStatus
    object Loading: ApiStatus
    data class Fail(val e: Throwable): ApiStatus
}