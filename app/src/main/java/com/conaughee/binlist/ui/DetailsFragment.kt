package com.conaguhee.binlist.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.conaguhee.binlist.dto.CardData
import com.conaguhee.binlist.viewModel.CardViewModel
import com.conaguhee.binlist.viewModel.NW_ERR
import com.conaguhee.binlist.viewModel.REQ_ERR
import com.conaughee.binlist.R
import com.conaughee.binlist.databinding.FragmentDetailsBinding
import java.util.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: CardViewModel by viewModels()
    private var requestBin: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        listeners()
        subscribers()
        visibleLayout(false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun bind(cardData: CardData) {
        val emptyText = ""
        visibleLayout(cardData.number != null)

        with(binding) {

            scheme.text = emptyText
            cardData.scheme?.let {
                scheme.text = "${cardData.scheme.substring(0, 1).uppercase()}${cardData.scheme.substring(1).lowercase()}"
            }

            type.text = emptyText
            cardData.type?.let {
                type.text = "${cardData.type.substring(0, 1).uppercase()}${cardData.type.substring(1).lowercase()}"
            }

            cardData.number?.let {
                when (cardData.number.length) {
                    16 -> cardNumber.text = "${requestBin.toString().substring(0, 4)} ${requestBin.toString().substring(4, 8)} **** ****"
                    12 -> cardNumber.text = "${requestBin.toString().substring(0, 4)} ${requestBin.toString().substring(4, 8)} ****"
                    else -> cardNumber.text = requestBin.toString()
                }
            }

            bankUrl.text = cardData.bank?.url ?: emptyText
            brand.text = cardData.brand ?: emptyText

            when (cardData.prepaid) {
                true -> prepaidYes.text = "Yes"
                false -> prepaidYes.text = "No"
                else -> {
                    prepaidYes.text = emptyText
                }
            }

            country.text = emptyText
            cardData.country?.let {
                it.emoji?.let { emoji ->
                    it.name?.let { name ->
                        country.text = "$emoji $name"
                    }
                }
            }
        }
    }

    private fun getData(bin: String, act: () -> Unit) {
        try {
            val binInt = Integer.parseInt(bin)
            viewModel.get(binInt, false)
        } catch (e: java.lang.NumberFormatException) {
            act()
        }
    }

    private fun visibleLayout(visible: Boolean) {
        binding.cardLayout.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        binding.bankLayout.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        binding.countryLayout.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    private fun listeners() {
        with(binding) {
            btnFind.setOnClickListener {
                if (binding.enterBin.text.toString().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.err_empty_enter),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    requestBin = Integer.parseInt(binding.enterBin.text.toString())
                    getData(binding.enterBin.text.toString()) {
                        binding.enterBin.setText("")
                    }
                }
            }
        }
    }

    private fun openBankOnMap(cardData: CardData) {
        val latitude = cardData.country?.latitude
        val longitude = cardData.country?.longitude

        if (latitude != null && longitude != null) {
            val uri = java.lang.String.format(Locale.ENGLISH, "geo:%s,%s", latitude, longitude)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }

    private fun subscribers() {
        viewModel.cardData.observe(viewLifecycleOwner) { cardData ->
            bind(cardData)

            binding.countryLayout.setOnClickListener {
                openBankOnMap(cardData)
            }
        }

        viewModel.errState.observe(viewLifecycleOwner) { err ->
            when (err) {
                REQ_ERR -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.err_req_mes),
                        Toast.LENGTH_LONG
                    ).show()
                }
                NW_ERR -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.err_nw_mes),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}