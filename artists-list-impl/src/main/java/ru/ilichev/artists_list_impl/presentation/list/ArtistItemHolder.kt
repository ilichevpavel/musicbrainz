package ru.ilichev.artists_list_impl.presentation.list

import androidx.recyclerview.widget.RecyclerView
import ru.ilichev.artists_list_impl.databinding.ItemArtistBinding
import ru.ilichev.artists_list_impl.presentation.list.item.ArtistsListItem

internal class ArtistItemHolder(
    private val binding: ItemArtistBinding,
    onClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var item: ArtistsListItem? = null

    init {
        itemView.setOnClickListener {
            item?.let { onClick(it.id) }
        }
    }

    fun bind(item: ArtistsListItem) {
        this.item = item

        binding.tvArtistTitle.text = item.title
    }
}