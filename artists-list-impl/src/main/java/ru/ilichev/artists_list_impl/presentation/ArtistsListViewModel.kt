package ru.ilichev.artists_list_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.ilichev.artists_list_impl.data.ArtistsRepository
import javax.inject.Inject

private const val INPUT_DELAY = 300L

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
internal class ArtistsListViewModel @Inject constructor(
    private val artistsRepository: ArtistsRepository,
    private val uiStateMapper: ArtistsListUiStateMapper
) : ViewModel() {

    private val searchInput = MutableStateFlow("")
    private val _state = MutableStateFlow<ArtistsListUiState>(ArtistsListUiState.Content())
    val state: StateFlow<ArtistsListUiState> = _state
    private val _sideEffect = MutableSharedFlow<ArtistsListSideEffect>()
    val sideEffect: SharedFlow<ArtistsListSideEffect> = _sideEffect

    init {
        viewModelScope.launch {
            searchInput
                .debounce(INPUT_DELAY)
                .filter { it.isNotBlank() }
                .flatMapLatest { query -> artistsRepository.getArtists(query) }
                .cachedIn(viewModelScope)
                .map(uiStateMapper::mapPagingData)
                .collectLatest { uiState -> _state.value = uiState }
        }
    }

    fun onAction(action: ArtistsListAction) {
        when (action) {
            is ArtistsListAction.OnSearchChanged -> handleQueryChanges(action.query)
            is ArtistsListAction.OnArtistClicked -> handleArtistsClick(action.id)
            is ArtistsListAction.OnRefreshLoadStateChanged -> handleRefreshLoadStateChanges(
                action.loadState,
                action.itemsCount
            )
        }
    }

    private fun handleQueryChanges(newQuery: String) {
        searchInput.value = newQuery

        if (newQuery.isBlank()) {
            _state.value = ArtistsListUiState.Content()
        }
    }

    private fun handleRefreshLoadStateChanges(loadState: LoadState, itemsCount: Int) {
        val withoutItems = itemsCount == 0
        when {
            loadState is LoadState.Error && withoutItems -> {
                _state.value = ArtistsListUiState.Error
            }
            loadState is LoadState.Loading && withoutItems -> {
                _state.value = ArtistsListUiState.FirstPagingLoading
            }
            loadState is LoadState.NotLoading && withoutItems && searchInput.value.isNotBlank() -> {
                _state.value = ArtistsListUiState.Empty
            }
        }
    }

    private fun handleArtistsClick(id: String) {
        viewModelScope.launch { _sideEffect.emit(ArtistsListSideEffect.OpenArtistDetails(id)) }
    }
}