package com.aziz.virginmoneytask.rest

import com.aziz.virginmoneytask.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface DirectoryAppRepository {
    val peopleresponseFlow: StateFlow<UIState>
    suspend fun getPeopleList()
}

class DirectoryAppRepositoryImpl(
    private val directoryAppApi: DirectoryAppApi
):DirectoryAppRepository {
    private val _peopleresponseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val peopleresponseFlow: StateFlow<UIState>
        get() = _peopleresponseFlow

    override suspend fun getPeopleList() {
        try {
            val response = directoryAppApi.peopleList()

            if (response.isSuccessful) {
                response.body()?.let {
                    _peopleresponseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _peopleresponseFlow.value = UIState.ERROR(IllegalStateException("People details are coming as null!"))
                }
            } else {
                _peopleresponseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _peopleresponseFlow.value = UIState.ERROR(e)
        }
    }
}