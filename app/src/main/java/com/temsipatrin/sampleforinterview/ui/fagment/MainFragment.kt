package com.temsipatrin.sampleforinterview.ui.fagment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temsipatrin.sampleforinterview.R
import com.temsipatrin.sampleforinterview.databinding.MainFragmentBinding
import com.temsipatrin.sampleforinterview.ui.adapter.CharactersAdapter
import com.temsipatrin.sampleforinterview.utils.remove
import com.temsipatrin.sampleforinterview.utils.show
import com.temsipatrin.sampleforinterview.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.main_fragment), CharactersAdapter.OnCardClickListener {

    private val viewModel by viewModel<MainViewModel>()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater,container,false)

        initView()
        observeState()

        return binding.root
    }

    private fun initView() {
        adapter = CharactersAdapter(this)
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (position + 1 == adapter.data.size) {
                    updateData()
                }
            }
        })
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                setState(it)
            }
        }
    }

    private fun setState(state: MainViewModel.State){
        when(state){
            is MainViewModel.State.CharactersLoaded ->{
                adapter.data = state.data
                hideError()
                hideLoading()
            }
            is MainViewModel.State.ShowLoading ->{
                hideError()
                showLoading()
            }
            is MainViewModel.State.ShowError -> {
                hideLoading()
                showError(state.stringId)
            }
        }

    }

    private fun showLoading(){
        binding.progressBar.show()
    }
    private fun hideLoading(){
        binding.progressBar.remove()
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

    private fun updateData(){
        viewModel.updateData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToCharacterInfoFragment(id)
        )
    }

}