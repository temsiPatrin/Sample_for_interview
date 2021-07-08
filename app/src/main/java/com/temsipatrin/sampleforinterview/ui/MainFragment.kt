package com.temsipatrin.sampleforinterview.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.temsipatrin.sampleforinterview.R
import com.temsipatrin.sampleforinterview.databinding.MainFragmentBinding
import com.temsipatrin.sampleforinterview.ui.adapter.CharactersAdapter
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

        viewModel.fetchData()
        return binding.root
    }

    private fun initView() {
        adapter = CharactersAdapter(this)
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
                Log.d("test",state.data.toString())


            }
            is MainViewModel.State.ShowLoading ->{

            }
            is MainViewModel.State.ShowError -> {

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id: Int) {

    }

}