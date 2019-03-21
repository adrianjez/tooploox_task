package com.hqapps.domain.model

data class ListResponse<T> (var resultCount: Int, var results: ArrayList<T>)