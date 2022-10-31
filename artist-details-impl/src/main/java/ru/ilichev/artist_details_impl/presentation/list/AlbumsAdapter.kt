package ru.ilichev.artist_details_impl.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.ilichev.artist_details_impl.databinding.ItemAlbumBinding
import ru.ilichev.artist_details_impl.presentation.list.item.AlbumListItem

internal class AlbumsAdapter : ListAdapter<AlbumListItem, AlbumsHolder>(AlbumsListItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context))
        return AlbumsHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
        holder.bind(getItem(position))
    }
}