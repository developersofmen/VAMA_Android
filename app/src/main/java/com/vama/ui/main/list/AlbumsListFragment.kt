package com.vama.ui.main.list

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vama.R
import com.vama.app.AppController
import com.vama.ui.composeviews.circularProgressComponent
import com.vama.ui.composeviews.gridView
import com.vama.ui.composeviews.showEmptyView
import com.vama.utils.AppUtils

class AlbumsListFragment : Fragment(R.layout.fragment_album_list) {
    companion object {
        fun newInstance() = AlbumsListFragment()
    }

    private lateinit var viewModel: AlbumListModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumListModel::class.java)
        subscribeObservers()
        checkInternetAndDisplayUI()
    }

    private fun subscribeObservers() {
        viewModel.getAlbumsResponse()?.observe(viewLifecycleOwner, Observer {
            view?.findViewById<ComposeView>(R.id.compose_view)?.setContent {
                if (it?.isNotEmpty() == true) {
                    gridView(it, parentFragmentManager)
                } else if (AppUtils.isInternet(AppController.applicationContext())) {
                    showEmptyView(
                        requireContext(),
                        getString(R.string.no_item_found),
                        viewModel,
                        this
                    )
                } else {
                    showEmptyView(
                        requireContext(),
                        getString(R.string.internet_not_available),
                        viewModel,
                        this
                    )
                }
            }
        })
    }

    fun checkInternetAndDisplayUI() {
        view?.findViewById<ComposeView>(R.id.compose_view)?.setContent {
            if (!AppUtils.isInternet(AppController.applicationContext())) {
                showEmptyView(
                    requireContext(),
                    getString(R.string.internet_not_available),
                    viewModel,
                    this
                )
            } else {
                circularProgressComponent()
            }
        }
    }
}