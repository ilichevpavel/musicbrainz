package ru.ilichev.artist_details_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ilichev.artist_details_impl.domain.GetArtistDetailsWithAlbumsUseCase
import javax.inject.Inject

internal class ArtistDetailsViewModel @Inject constructor(
    private val id: String,
    private val getArtistDetailsWithAlbumsUseCase: GetArtistDetailsWithAlbumsUseCase,
    private val artistDetailsUiStateMapper: ArtistDetailsUiStateMapper
) : ViewModel() {

    private val _state = MutableStateFlow<ArtistDetailsUiState>(ArtistDetailsUiState.Loading)
    val state: StateFlow<ArtistDetailsUiState> = _state

    init {
        loadContentData()
    }

    fun onAction(action: ArtistDetailsAction) {
        when (action) {
            ArtistDetailsAction.OnRetryClicked -> loadContentData()
        }
    }

    private fun loadContentData() {
        _state.value = ArtistDetailsUiState.Loading

        viewModelScope.launch {
            getArtistDetailsWithAlbumsUseCase(id)
                .onSuccess { artistDetails ->
                    _state.value = artistDetailsUiStateMapper.getContentState(artistDetails)
                }
                .onFailure {
                    _state.value = ArtistDetailsUiState.Error
                }
        }
    }
}