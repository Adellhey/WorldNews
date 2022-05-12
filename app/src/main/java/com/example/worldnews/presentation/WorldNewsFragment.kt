package com.example.worldnews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldnews.R
import com.example.worldnews.data.Results
import com.example.worldnews.databinding.FragmentWorldNewsBinding
import com.example.worldnews.presentation.adapters.WorldNewsAdapter

class WorldNewsFragment : Fragment() {

    private val viewModel: WorldNewsViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[WorldNewsViewModel::class.java]
    }
    private val adapter = WorldNewsAdapter(this)

    private var _binding: FragmentWorldNewsBinding? = null
    private val binding: FragmentWorldNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentWorldNews == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorldNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
        observeViewModel()
    }

    private fun initWidgets() {
        binding.rvWorldNews.adapter = adapter
        binding.rvWorldNews.layoutManager = LinearLayoutManager(context)

        adapter.onWorldNewsClickListener = object : WorldNewsAdapter.OnWorldNewsClickListener {
            override fun onWorldNewsClick(results: Results) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.world_news_container, WorldNewsDetailFragment.newInstance(
                            results.title,
                            results.description,
                            results.fullDescription,
                            results.imageUrl,
//                            results.creator?.first(),
                            results.content
                        )
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.worldNewsList.observe(viewLifecycleOwner) {
            adapter.worldNewsList = it
        }

        viewModel.peekProgressLiveData().observe(viewLifecycleOwner) {
            binding.progressBarLoading.isVisible = it
        }

        binding.buttonRussianNews.setOnClickListener {
            viewModel.initNews(TypeOfNews.RUSSIAN)
            viewModel.worldNewsList.observe(viewLifecycleOwner) {
                adapter.worldNewsList = it
            }
        }

        binding.buttonAmericanNews.setOnClickListener {
            viewModel.initNews(TypeOfNews.AMERICAN)
            viewModel.worldNewsList.observe(viewLifecycleOwner) {
                adapter.worldNewsList = it
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(): WorldNewsFragment {
            return WorldNewsFragment()
        }
    }
}