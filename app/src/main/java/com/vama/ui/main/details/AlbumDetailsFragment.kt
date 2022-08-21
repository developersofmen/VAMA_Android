package com.vama.ui.main.details

import Results
import android.os.Bundle
import android.view.View
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.vama.R
import com.vama.ui.composeviews.showDetails


class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {
    private lateinit var mResult: Results

    companion object {
        fun newInstance(result: Results) = AlbumDetailsFragment().apply {
            mResult = result
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById<ComposeView>(R.id.compose_view)?.setContent {
            MaterialTheme {
                showDetails(requireContext(), mResult)
            }
        }
    }
}