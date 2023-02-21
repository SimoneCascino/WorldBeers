package it.simonecascino.feature_home_presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.simonecascino.core.architecture.BaseViewModel
import it.simonecascino.destination.Destinations
import it.simonecascino.feature_home_presentation.detail.models.BeerDetailUI
import it.simonecascino.home_domain.usecases.GetBeerUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBeerUseCase: GetBeerUseCase
): BaseViewModel<DetailState>() {

    private val beerId = savedStateHandle.get<String>(Destinations.DetailScreen.KEY_beerId)?.toInt()

    override val initialState = DetailState()

    init {

        viewModelScope.launch {

            beerId?.let {
                val beer = getBeerUseCase(it)
                changeState {
                    copy(
                        beerDetailUI = BeerDetailUI.fromDomain(beer)
                    )
                }
            }

        }

    }

}