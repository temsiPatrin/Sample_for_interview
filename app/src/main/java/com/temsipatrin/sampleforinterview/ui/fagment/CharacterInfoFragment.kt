package com.temsipatrin.sampleforinterview.ui.fagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.temsipatrin.sampleforinterview.R
import com.temsipatrin.sampleforinterview.databinding.FragmentCharacterInfoBinding
import com.temsipatrin.sampleforinterview.ui.models.CharacterUi
import com.temsipatrin.sampleforinterview.utils.remove
import com.temsipatrin.sampleforinterview.utils.show
import com.temsipatrin.sampleforinterview.viewmodels.CharacterInfoViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {

    private val viewModel by viewModel<CharacterInfoViewModel>{
        parametersOf(CharacterInfoFragmentArgs.fromBundle(requireArguments()).id)
    }

    private var _binding: FragmentCharacterInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterInfoBinding.inflate(inflater,container,false)

        observeState()

        return binding.root
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                setState(it)
            }
        }
    }

    private fun setState(state: CharacterInfoViewModel.State){
        when(state){
            is CharacterInfoViewModel.State.CharacterInfoLoaded ->{
                showData(state.data)
                hideError()
                hideLoading()
            }
            is CharacterInfoViewModel.State.ShowLoading ->{
                hideError()
                showLoading()
            }
            is CharacterInfoViewModel.State.ShowError -> {
                hideLoading()
                showError(state.stringId)
            }
        }
    }

    private fun showLoading(){
        binding.loading.show()
    }

    private fun hideLoading(){
        binding.loading.remove()
    }

    private fun showError(@StringRes message: Int){
        binding.imageError.show()
        binding.textError.show()
        binding.textError.text = getString(message)
    }

    private fun hideError(){
        binding.imageError.remove()
        binding.textError.remove()
    }

    private fun showData(character: CharacterUi) {
        binding.name.text = character.name
        binding.status.text = character.status
        binding.species.text = character.species
        binding.gender.text = character.gender
        binding.origin.text = character.origin
        binding.location.text = character.location
        Glide.with(requireContext())
            .load(character.image)
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
            .into(binding.image)
    }
}