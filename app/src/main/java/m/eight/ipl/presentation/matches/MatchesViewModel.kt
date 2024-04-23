package m.eight.ipl.presentation.matches

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import m.eight.ipl.common.Resource
import m.eight.ipl.domain.model.Match
import m.eight.ipl.domain.usecase.GetMatchesUseCase
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(savedStateHandle: SavedStateHandle, private val getMatchesUseCase: GetMatchesUseCase) : ViewModel() {

    private val _state = mutableStateOf(MatchesState())
    val state: State<MatchesState> get() = _state

    init {
        savedStateHandle.get<Int>("currentMatchDay")?.let { getMatches(it) }
    }

    private fun getMatches(matchDay: Int) {
        getMatchesUseCase(matchDay).onEach { result ->
            when (result) {
                is Resource.Error -> _state.value = state.value.copy(isLoading = false, error = result.message ?: "An unexpected error occurred.")
                is Resource.Loading -> _state.value = state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = state.value.copy(isLoading = false, standings = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

}

data class MatchesState(
    val isLoading: Boolean = false,
    val standings: List<Match> = emptyList(),
    val error: String = ""
)