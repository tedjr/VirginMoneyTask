package com.aziz.virginmoneytask.ui.peoplelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aziz.virginmoneytask.rest.DirectoryAppRepository
import com.aziz.virginmoneytask.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class PeopleViewModel  (private val directoryAppRepository: DirectoryAppRepository,
                        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
                        private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel(){

    private val _peopleLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val peopleLiveData: LiveData<UIState> get() = _peopleLiveData

    fun subscribeToPeopleList() {
        _peopleLiveData.postValue(UIState.LOADING())
        collectUsersList()
        launch {
            directoryAppRepository.getPeopleList()
        }
    }

    private fun collectUsersList() {
        launch {
            /******
             *  ! Important - Before start testing please uncomment the testing codes.
             */
            //_usersLiveData.postValue(UIState.SUCCESS(getOutputData()))

            /******
             *  ! Important - Before start testing please comment the testing codes.
             */
            directoryAppRepository.peopleresponseFlow.collect { uiState ->
                when(uiState) {
                    is UIState.LOADING -> { _peopleLiveData.postValue(uiState) }
                    is UIState.SUCCESS -> { _peopleLiveData.postValue(uiState) }
                    is UIState.ERROR -> { _peopleLiveData.postValue(uiState) }
                }
            }
        }
    }
}