package ru.ilichev.artists_list_impl.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import ru.ilichev.artists_list_impl.databinding.ItemIdleStubBinding

internal class ArtistsListLoadStateAdapter(
    private val onRetry: () -> Unit
) : LoadStateAdapter<ArtistsListIdleStubHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ArtistsListIdleStubHolder {
        val binding = ItemIdleStubBinding.inflate(LayoutInflater.from(parent.context))
        return ArtistsListIdleStubHolder(binding, onRetry)
    }

    override fun onBindViewHolder(holder: ArtistsListIdleStubHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}