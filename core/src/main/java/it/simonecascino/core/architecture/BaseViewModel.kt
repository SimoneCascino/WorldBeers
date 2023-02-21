package it.simonecascino.core.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S: BaseState>: ViewModel() {

    protected abstract val initialState: S

    private val state: MutableStateFlow<S> by lazy {
        MutableStateFlow(initialState)
    }

    val currentState: StateFlow<S>
        get() = state

    protected fun changeState(changer: S.() -> S){
        state.value = state.value.changer()
    }

}