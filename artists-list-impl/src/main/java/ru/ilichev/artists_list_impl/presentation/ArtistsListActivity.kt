package ru.ilichev.artists_list_impl.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.ilichev.artist_details_api.ArtistDetailsApi
import ru.ilichev.artist_details_api.ArtistDetailsScreenProvider
import ru.ilichev.artists_list_impl.R
import ru.ilichev.artists_list_impl.databinding.ActivityArtistsListBinding
import ru.ilichev.artists_list_impl.di.DaggerArtistsListComponent
import ru.ilichev.artists_list_impl.presentation.list.ArtistsListAdapter
import ru.ilichev.artists_list_impl.presentation.list.ArtistsListLoadStateAdapter
import ru.ilichev.common.di.factory.getComponentFactory
import ru.ilichev.common_network.di.NetworkApi
import javax.inject.Inject

internal class ArtistsListActivity : AppCompatActivity(R.layout.activity_artists_list) {

    @Inject
    lateinit var artistDetailsScreenProvider: ArtistDetailsScreenProvider

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ArtistsListViewModel

    private val binding by viewBinding(ActivityArtistsListBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        ArtistsListAdapter {
            viewModel.onAction(ArtistsListAction.OnArtistClicked(it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDI()
        initViews()
        observeViewModel()
    }

    private fun initDI() {
        DaggerArtistsListComponent.factory()
            .create(
                networkApi = getComponentFactory()[NetworkApi::class],
                artistDetailsApi = getComponentFactory()[ArtistDetailsApi::class]
            )
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[ArtistsListViewModel::class.java]
    }

    private fun initViews() = with(binding) {
        initContentList()
        btnReply.setOnClickListener { adapter.refresh() }
        etSearch.doAfterTextChanged { input ->
            viewModel.onAction(ArtistsListAction.OnSearchChanged(input?.toString().orEmpty()))
        }
    }

    private fun initContentList() = with(binding) {
        rvContent.adapter = adapter.withLoadStateFooter(ArtistsListLoadStateAdapter {
            adapter.retry()
        })
        rvContent.layoutManager = LinearLayoutManager(
            this@ArtistsListActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        adapter.addLoadStateListener { state ->
            viewModel.onAction(
                ArtistsListAction.OnRefreshLoadStateChanged(state.refresh, adapter.itemCount)
            )
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collectLatest(::renderState)
        }
        lifecycleScope.launchWhenCreated {
            viewModel.sideEffect.collect(::handleSideEffect)
        }
    }

    private suspend fun renderState(state: ArtistsListUiState) {
        binding.errorStub.isVisible = state is ArtistsListUiState.Error
        binding.progress.isVisible = state is ArtistsListUiState.FirstPagingLoading
        binding.tvEmptyStubTitle.isVisible = state is ArtistsListUiState.Empty

        if (state is ArtistsListUiState.Content) {
            lifecycleScope.launch { adapter.submitData(state.items) }
        }
    }

    private fun handleSideEffect(sideEffect: ArtistsListSideEffect) {
        when (sideEffect) {
            is ArtistsListSideEffect.OpenArtistDetails -> {
                startActivity(artistDetailsScreenProvider.getIntent(this, sideEffect.id))
            }
        }
    }
}