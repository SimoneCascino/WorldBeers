package it.simonecascino.feature_home_presentation.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.simonecascino.core.architecture.BaseViewModel
import it.simonecascino.feature_home_presentation.home.models.BeerHomeUI
import it.simonecascino.home_domain.models.BeerDomain
import it.simonecascino.home_domain.usecases.DownloadBeersUseCase
import it.simonecascino.home_domain.usecases.GetBeersUseCase
import it.simonecascino.home_domain.usecases.SearchUseCase
import it.simonecascino.home_domain.utils.ApiStatus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getBeersUseCase: GetBeersUseCase,
    downloadBeersUseCase: DownloadBeersUseCase,
    private val searchUseCase: SearchUseCase
): BaseViewModel<HomeState>() {

    override val initialState: HomeState = HomeState()

    private val beers = getBeersUseCase()
    private val apiStatus = downloadBeersUseCase()

    init {

        viewModelScope.launch {

            combine(
                beers,
                apiStatus
            ){ beers: List<BeerDomain>, apiStatus: ApiStatus ->

                HomeState(
                    beers = beers.map { BeerHomeUI.fromDomainModel(it)},
                    loading = apiStatus is ApiStatus.Loading && beers.isEmpty()
                )

            }.collect{
                changeState { it }
            }

        }
    }

    fun search(query: String){

        changeState {
            copy(query = query)
        }

        viewModelScope.launch {

            if(query.isEmpty())
                beers.collect{
                    changeState {
                        copy(beers = it.map { BeerHomeUI.fromDomainModel(it)})
                    }
                }

            else searchUseCase(query).collectLatest {
                changeState {
                    copy(beers = it.map { BeerHomeUI.fromDomainModel(it)})
                }
            }
        }
    }

}