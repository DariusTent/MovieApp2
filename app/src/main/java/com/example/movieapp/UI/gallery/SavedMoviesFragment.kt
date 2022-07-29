package com.example.movieapp.UI.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.movieapp.UI.tab_activity.ui.main.SectionsPagerAdapter
import com.example.movieapp.databinding.FragmentSavedMoviesBinding
import com.google.android.material.tabs.TabLayout

class SavedMoviesFragment : Fragment() {

    private var _binding: FragmentSavedMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val savedViewMode =
            ViewModelProvider(this).get(SavedViewModel::class.java)

        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

    //   private fun getMovies(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}