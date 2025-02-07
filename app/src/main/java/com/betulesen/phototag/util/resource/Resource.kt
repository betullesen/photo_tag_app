package com.betulesen.phototag.util.resource

data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR;
    }

    companion object {

        fun <T> loading(nothing: Nothing?): Resource<T> {
            return Resource(Status.LOADING)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(error: String): Resource<T> {
            return Resource(Status.ERROR, message = error)
        }
    }
}