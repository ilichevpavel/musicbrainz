package ru.ilichev.artist_details_impl.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import ru.ilichev.artist_details_impl.R
import ru.ilichev.artist_details_impl.databinding.ActivityArtistDetailsBinding
import ru.ilichev.artist_details_impl.di.DaggerArtistDetailsComponent
import ru.ilichev.artist_details_impl.presentation.list.AlbumsAdapter
import ru.ilichev.common.di.component.CommonApi
import ru.ilichev.common.di.factory.getComponentFactory
import ru.ilichev.common_network.di.NetworkApi
import javax.inject.Inject

internal class ArtistDetailsActivity : AppCompatActivity(R.layout.activity_artist_details) {

    companion object {
        private const val ID_KEY = "id"

        fun createIntent(context: Context, id: String): Intent {
            return Intent(context, ArtistDetailsActivity::class.java).apply {
                putExtra(ID_KEY, id)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ArtistDetailsViewModel

    private val binding by viewBinding(ActivityArtistDetailsBinding::bind)
    private val adapter: AlbumsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AlbumsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDI()
        initViews()
        observeViewModel()
    }

    private fun initDI() {
        val id = intent.getStringExtra(ID_KEY) ?: error("Artist details id must be set")

        DaggerArtistDetailsComponent.factory()
            .create(
                id = id,
                commonApi = getComponentFactory()[CommonApi::class],
                networkApi = getComponentFactory()[NetworkApi::class]
            ).inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[ArtistDetailsViewModel::class.java]
    }

    private fun initViews() = with(binding) {
        rvContent.adapter = adapter
        rvContent.layoutManager =
            LinearLayoutManager(this@ArtistDetailsActivity, LinearLayoutManager.VERTICAL, false)

        btnReply.setOnClickListener { viewModel.onAction(ArtistDetailsAction.OnRetryClicked) }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collectLatest(::renderState)
        }
    }

    private fun renderState(state: ArtistDetailsUiState) = with(binding) {
        progress.isVisible = state is ArtistDetailsUiState.Loading
        errorStub.isVisible = state is ArtistDetailsUiState.Error

        if (state is ArtistDetailsUiState.Content) {
            tvTitle.text = state.title
            adapter.submitList(state.albums)
        }
    }
}