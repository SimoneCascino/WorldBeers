package it.simonecascino.home_domain.usecases

import it.simonecascino.home_domain.repositories.BeerRepository
import javax.inject.Inject

class DownloadBeersUseCase @Inject constructor(
    private val repository: BeerRepository
) {

    operator fun invoke() = repository.downloadBeers()

}