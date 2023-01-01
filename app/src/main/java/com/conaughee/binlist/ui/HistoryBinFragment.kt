package com.conaguhee.binlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.conaguhee.binlist.dto.CardBin
import com.conaguhee.binlist.ui.adapter.CardBinAdapter
import com.conaguhee.binlist.ui.adapter.OnInteractionListener
import com.conaguhee.binlist.viewModel.CardViewModel
import com.conaughee.binlist.databinding.FragmentHistoryBinding

@AndroidEntryPoint
class HistoryBinFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val adapter = CardBinAdapter(object : OnInteractionListener {
            override fun onRemove(bin: CardBin) {
                viewModel.removeById(bin.id)
            }
        })
        binding.binList.adapter = adapter

        viewModel.binData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}