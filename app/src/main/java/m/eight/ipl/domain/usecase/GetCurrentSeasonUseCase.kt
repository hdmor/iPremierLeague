package m.eight.ipl.domain.usecase

import m.eight.ipl.data.source.remote.dto.seasons.toCurrentSeason
import m.eight.ipl.domain.model.CurrentSeason
import m.eight.ipl.domain.repository.PlRepository

class GetCurrentSeasonUseCase(private val repository: PlRepository) {
    suspend operator fun invoke(): CurrentSeason = repository.getSeasons().currentSeasonDto.toCurrentSeason()
}