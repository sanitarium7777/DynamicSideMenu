package com.github.sanitarium7777.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.sanitarium7777.R
import com.github.sanitarium7777.databinding.FragmentMessageBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment : Fragment() {

    private val viewModel: MessageFragmentViewModel by viewModel()
    lateinit var binding: FragmentMessageBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString(ARG_MESSAGE)?.let {
            viewModel.message.value = it
        }
    }

    companion object {
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        @JvmStatic
        fun newInstance(message: String) = MessageFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MESSAGE, message)
            }
        }
    }


}
