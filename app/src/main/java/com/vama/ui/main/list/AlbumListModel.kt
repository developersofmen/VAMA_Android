package com.vama.ui.main.list

import Results
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vama.repositories.datasource.AlbumRepositories

class AlbumListModel : ViewModel() {
    private var mAlbumRepositories: AlbumRepositories? = null

    init {
        mAlbumRepositories = AlbumRepositories()
        getAlbum()
    }

    fun getAlbum(): Unit? {
        return mAlbumRepositories?.getAlbumsData()
    }

    fun getAlbumsResponse(): LiveData<List<Results>?>? {
        return mAlbumRepositories?.getAlbumsResponse()
    }
}