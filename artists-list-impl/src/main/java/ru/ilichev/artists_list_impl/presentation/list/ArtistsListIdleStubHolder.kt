package ru.ilichev.artists_list_impl.presentation.list

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ru.ilichev.artists_list_impl.databinding.ItemIdleStubBinding

internal class ArtistsListIdleStubHolder(
    private val binding: ItemIdleStubBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnIdleRetry.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        binding.btnIdleRetry.isVisible = loadState is LoadState.Error
        binding.tvIdleError.isVisible = loadState is LoadState.Error
        binding.idleProgress.isVisible = loadState is LoadState.Loading
    }
}