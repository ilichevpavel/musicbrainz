package ru.ilichev.common.extensions

fun <T> Result<T>.mapFailure(transform: (Throwable) -> Throwable): Result<T> {
    return when {
        isSuccess -> this
        isFailure -> Result.failure(transform(exceptionOrNull()!!))
        else -> error("Impossible situation")
    }
}