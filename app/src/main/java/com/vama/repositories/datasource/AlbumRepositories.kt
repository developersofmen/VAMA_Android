package com.vama.repositories.datasource

import Results
import androidx.lifecycle.MutableLiveData
import com.vama.R
import com.vama.app.AppController
import com.vama.repositories.datasource.local.RealmDBHelper
import com.vama.repositories.datasource.remote.ApiResponseHandler
import com.vama.repositories.datasource.remote.NetworkService
import com.vama.utils.AppConstant.SP_COPYRIGHT
import com.vama.utils.AppUtils
import com.vama.utils.SharedPreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumRepositories {
    private val mAlbums: MutableLiveData<List<Results>> = MutableLiveData()
    private var isLocalData = false
    fun getAlbumsData() {
        if (RealmDBHelper.realmFetchAllRecords().isNotEmpty()) {
            isLocalData = true
            mAlbums.value = RealmDBHelper.realmFetchAllRecords()
        }

        if (AppUtils.isInternet(AppController.applicationContext())) {
            albumsListAPICall()
        }
    }

    private fun albumsListAPICall() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkService.callAlbumsListAPI()

            if (response is ApiResponseHandler.Success) {
                val albumListResponse = response.data
                withContext(Dispatchers.Main) {
                    mAlbums.value = albumListResponse.feed.results
                }
                if (response.data.feed.copyright?.isNotEmpty() == true) {
                    SharedPreferenceHelper.updatePreferences(
                        SP_COPYRIGHT,
                        response.data.feed.copyright
                    )
                }
                val resultList = albumListResponse.feed.results
                RealmDBHelper.realmStore(resultList)
            }
            if (response is ApiResponseHandler.Error) {
                if (!isLocalData) {
                    withContext(Dispatchers.Main) {
                        mAlbums.value = ArrayList()
                        val context = AppController.applicationContext()
                        AppUtils.showToast(
                            context,
                            context.getString(R.string.something_went_wrong)
                        )
                    }
                }
            }
        }
    }

    fun getAlbumsResponse(): MutableLiveData<List<Results>> {
        return mAlbums
    }
}