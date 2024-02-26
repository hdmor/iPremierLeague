package m.eight.ipl.data.repository

import m.eight.ipl.data.source.remote.ApiService
import m.eight.ipl.data.source.remote.dto.matches.MatchesDto
import m.eight.ipl.data.source.remote.dto.seasons.SeasonsDto
import m.eight.ipl.data.source.remote.dto.standings.StandingsDto
import m.eight.ipl.domain.repository.PlRepository

class PlRepositoryImpl(private val apiService: ApiService) : PlRepository {
    override suspend fun getSeasons(): SeasonsDto = apiService.getSeasons()
    override suspend fun getMatches(matchDay: Int): MatchesDto = apiService.getMatches(matchDay = matchDay)
    override suspend fun getStandings(season: Int, matchDay: Int): StandingsDto = apiService.getStandings(season = season, matchDay = matchDay)
}