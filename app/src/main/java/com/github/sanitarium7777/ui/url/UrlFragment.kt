package com.github.sanitarium7777.ui.url

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.sanitarium7777.R
import com.github.sanitarium7777.databinding.FragmentUrlBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UrlFragment : Fragment() {

    private val viewModel: UrlFragmentViewModel by viewModel()
    lateinit var binding: FragmentUrlBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_url, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString(ARG_URL)?.let {
            viewModel.url.value = it
        }
    }

    companion object {
        private const val ARG_URL = "ARG_URL"
        @JvmStatic
        fun newInstance(url: String) = UrlFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_URL, url)
            }
        }
    }
}
