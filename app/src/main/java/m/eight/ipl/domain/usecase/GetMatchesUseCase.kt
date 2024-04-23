package m.eight.ipl.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m.eight.ipl.common.Resource
import m.eight.ipl.data.source.remote.dto.matches.toMatches
import m.eight.ipl.domain.model.Match
import m.eight.ipl.domain.repository.PlRepository
import retrofit2.HttpException
import java.io.IOException

class GetMatchesUseCase(private val repository: PlRepository) {

    operator fun invoke(matchDay: Int): Flow<Resource<List<Match>>> = flow {
        try {
            emit(Resource.Loading())
            val matches = repository.getMatches(matchDay = matchDay).toMatches()
            emit(Resource.Success(data = matches))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred."))
        } catch (io: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, Check your internet connection."))
        }
    }
}