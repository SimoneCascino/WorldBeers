package it.simonecascino.home_domain.usecases

import it.simonecascino.home_domain.repositories.BeerRepository
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(
    private val repository: BeerRepository
) {

    suspend operator fun invoke(id: Int) = repository.getBeer(id)

}