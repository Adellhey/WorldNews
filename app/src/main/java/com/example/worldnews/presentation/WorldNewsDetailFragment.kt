package com.example.worldnews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.worldnews.R
import com.example.worldnews.databinding.FragmentWorldNewsDetailBinding
import com.example.worldnews.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class WorldNewsDetailFragment : Fragment() {
    private var title: String? = null
    private var description: String? = null
    private var fullDescription: String? = null
    private var image: String? = null
    private var content: String? = null

    private var _binding: FragmentWorldNewsDetailBinding? = null
    private val binding: FragmentWorldNewsDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentWorldNewsDetail == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            description = it.getString(ARG_DESCRIPTION)
            fullDescription = it.getString(ARG_FULL_DESCRIPTION)
            image = it.getString(ARG_IMAGE)
            content = it.getString(ARG_CONTENT)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorldNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager2.adapter = ViewPagerAdapter(description, image)
        val tabNames: Array<String> = arrayOf(getString(R.string.button_desc), getString(R.string.button_image))
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(
            title: String?,
            description: String?,
            fullDescription: String?,
            image: String?,
            content: String?

        ) =
            WorldNewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCRIPTION, description)
                    putString(ARG_FULL_DESCRIPTION, fullDescription)
                    putString(ARG_IMAGE, image)
                    putString(ARG_CONTENT, content)
                }
            }
    }
}

private const val ARG_TITLE = "title"
private const val ARG_DESCRIPTION = "description"
private const val ARG_FULL_DESCRIPTION = "full_description"
private const val ARG_IMAGE = "image"
private const val ARG_CONTENT = "content"